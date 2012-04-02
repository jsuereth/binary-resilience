trait Foo {
  def foo: Int = bar.toInt
  def bar = "5"
}
object Foo {
  def foo: Foo = new Foo {}
}
