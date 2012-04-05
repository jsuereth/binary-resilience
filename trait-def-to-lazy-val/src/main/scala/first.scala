trait Foo {
  def foo: Int = 5
}


object Main {
  def main(args: Array[String]): Unit = {
    println((new Foo {}).foo)
  }
}
