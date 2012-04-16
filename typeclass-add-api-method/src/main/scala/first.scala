trait Serializable[T] {
  def encode(t: T): Array[Byte]
}

object Serializable extends TypeTraitInstances {
  final def encode[T: Serializable](s: T): Array[Byte] = 
    implicitly[Serializable[T]].encode(s)
}

trait TypeTraitInstances {
  implicit object intSerializable extends Serializable[Int] {
     def encode(t: Int): Array[Byte] = 
       Array((t >>> 24).toByte, 
             (t >> 16 & 0xff).toByte, 
             (t >> 8 & 0xff).toByte, 
             (t & 0xff).toByte)
  }
}

trait Foo
object Foo {
  implicit object FooSerializable extends Serializable[Foo] {
    def encode(t: Foo): Array[Byte] = Array(1.toByte,2.toByte,3.toByte)
  }
}


object Main {
  def main(args: Array[String]): Unit = {
    println(Serializable.encode(5) mkString "-")
    println(Serializable.encode(new Foo {}) mkString "-")
  }
}
