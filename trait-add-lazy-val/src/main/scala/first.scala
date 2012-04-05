trait Foo {
  def x = "HI"
}

class Bar(y: Int) extends Foo {
  def bar = x + y
}

object Main {
  def main(args: Array[String]): Unit = {
    val b = new Bar(1)
    println(b.bar)
  }
}
