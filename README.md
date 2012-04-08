# Binary Resilience test-bed #

This repository contains a bunch of tests for binary compatiblity.   Creating automated tests for failures is difficult, because not all binary incompatible changes cause exceptions.  In fact, the dangerous kind silently pass even the most innocent test.

This repository is an attempt to *prove* broken behavior via minimal test cases that highlight issues for users.   The passing test cases should give users an idea of how to proceed in the land of Scala and acheive their own binary compatible libraries.

Also, this code should be a hint that automated tool support (like typesafe's MiMa) are an almost necessity of library releases to ensure binary compatibility.


## The Build ##

The build is an sbt project.  Simply run sbt in the base directory.  SBT will discover all sub projects and wire them into the build.

### Running a test ###

To see the output of a single binary compatiblity test, run:

    sbt> <project-name>/bc-run

You'll see a result like the following:

    > trait-lazy-val-to-def/bc-run
    [info] Compiling 1 Scala source to /home/jsuereth/projects/personal/binary-resilience/trait-lazy-val-to-def/target/scala-2.9.1/classes...
    [info] Compiling 1 Scala source to /home/jsuereth/projects/personal/binary-resilience/trait-lazy-val-to-def/target/scala-2.9.1/second-compile-classes...
    [info] Running Main 
    Calculating Foo.x
    1
    1

The above is actually a failed test.   We're working on better ways to test effects besides println statements.


### Seeing the code ###

For any of the tests, you can run:

    sbt> <project-name>/bc-show-diff

And you'll get an output like the following:

    > trait-lazy-val-to-def/bc-show-diff
    trait Foo {                                | trait Foo {
      lazy val x = 1                           |   def x = { println("Calculating Foo.x"); 1 }
    }                                          | }
                                               | 
    object Main {                              | 
                                               | 
      def main(args: Array[String]): Unit =  { | 
        val tmp = new Foo {}                   | 
        println(tmp.x)                         | 
        println(tmp.x)                         | 
      }                                        | 
    }                                          | 


which will let you see how the test is set up, and what is changing.


# BSD License #

Copyright (c) 2012, Josh Suereth
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

