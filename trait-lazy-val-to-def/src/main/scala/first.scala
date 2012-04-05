trait Foo {
  lazy val x = 1
}

object Main {

  def main(args: Array[String]): Unit = 
    println(new Foo {}.x)
}
