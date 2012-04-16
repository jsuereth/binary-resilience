class A {
  lazy val a1 = {
    println("a1")
    1
  }
}
class B extends A {
  lazy val b = {
    println("b")
    3
  }
}
object Main {
  def main(args: Array[String]): Unit = {
    val b = new B
    println(b.a1)
    println(b.b)
  }
}
