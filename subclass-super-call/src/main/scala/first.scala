
abstract class Foo {}

class Bar {
  override def toString = "BAR + " + super.toString
}


object Main {
  def main(args: Array[String]): Unit = 
    println(new Bar)
}
