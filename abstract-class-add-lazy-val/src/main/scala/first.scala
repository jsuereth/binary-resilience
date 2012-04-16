abstract class Foo {}

class Bar extends Foo {}

object Main {
  def main(args: Array[String]): Unit = {
    val b = new Bar
    println(b)
    println(b)
  }
}
