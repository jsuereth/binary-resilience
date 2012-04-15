import java.nio.ByteBuffer


trait Serializable[T] {
  def encode(t: T): Array[Byte]
  def decode(t: ByteBuffer): T
}

object Serializable extends TypeTraitInstances {
  final def encode[T: Serializable](s: T): Array[Byte] =
    implicitly[Serializable[T]].encode(s)
  def decode[T: Serializable](buf: ByteBuffer): T =
    implicitly[Serializable[T]].decode(buf)
}

trait TypeTraitInstances {
  implicit object intSerializable extends Serializable[Int] {
     def encode(t: Int): Array[Byte] = 
       Array((t >>> 24).toByte, 
             (t >> 16 & 0xff).toByte, 
             (t >> 8 & 0xff).toByte, 
             (t & 0xff).toByte)

     def decode(buf: ByteBuffer): Int = 1
  }
}
