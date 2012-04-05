trait Foo {
  lazy val x = "HI"
}
object Main {
  def main(args: Array[String]): Unit = 
    println(new Foo {}.x)
}
