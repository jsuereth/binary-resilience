
abstract class Foo {}

class Bar extends Foo {
  override def toString = "BAR + " + super.toString
}


object Main {
  def main(args: Array[String]): Unit = 
    println(new Bar)
}
