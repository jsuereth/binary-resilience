trait A {
  println("A initialized")
  def foo(): Unit = 
    println("a")
}

trait B extends A {
  println("B initialized")
  override def foo(): Unit = {
    super.foo()
    println("b")
  }
}

trait C extends A {
  println("C initialized")
  override def foo(): Unit = {
    super.foo()
    println("c")
  }
}   

abstract class Foo extends A with B

class Bar extends Foo with C


object Main {
  def main(args: Array[String]): Unit =
    println(new Bar)
}
