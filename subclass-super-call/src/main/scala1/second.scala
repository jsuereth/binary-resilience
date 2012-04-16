
abstract class Foo {
  override def toString = "Foo's string!"
}

class Bar {
  override def toString = "BAR + " + super.toString
}
