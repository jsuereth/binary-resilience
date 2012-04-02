trait Foo {
  def foo: Int = 5
}

object Foo {
  def foo: Foo = new Foo {}
}


object Main {
  def main(args: Array[String]): Unit = {
    println((new Foo {}).foo)
  }
}
