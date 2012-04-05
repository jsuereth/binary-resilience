trait Foo {
  def bar = 1
}

object Main {
  def main(args: Array[String]): Unit =
    println(new Foo {}.bar)
}
