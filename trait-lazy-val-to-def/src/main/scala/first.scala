trait Foo {
  lazy val x = 1
}

object Main {

  def main(args: Array[String]): Unit =  {
    val tmp = new Foo {}
    println(tmp.x)
    println(tmp.x)
  }
}
