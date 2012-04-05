trait Foo {
  def x = "HI"
  def y = 1
}
object Main {
  def main(args: Array[String]): Unit =
    println(new Foo {}.x)
}
