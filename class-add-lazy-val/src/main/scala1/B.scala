class A {
  lazy val a1 = {
    println("a1")
    a2
    1
  }
  lazy val a2 = {
    println("a2")
    2
  }
}
