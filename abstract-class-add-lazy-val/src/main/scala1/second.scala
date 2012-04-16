abstract class Foo {
  override lazy val toString = { println("calculating FOO!"); "New Foo String" }
}

