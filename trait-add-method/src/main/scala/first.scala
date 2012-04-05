trait MyInterface {
  def method1 = "HI GUYS!"
}

object Main {
  def main(args: Array[String]): Unit = {
    val impl = new MyInterface {}
    println(impl.method1)
  }
}
