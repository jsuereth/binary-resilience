trait Foo {
  val x = "HI"
}

class Bar(z: Int) extends Foo {
  def bar = x + z
}

object Main {
  def main(args: Array[String]): Unit = {
    val b = new Bar(1)
    println(b.bar)
    println(b.x)
  }
}
