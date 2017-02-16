package tech.christopherdavenport.geo4s.structures

import spire.math._

case class Ellipsoid[A](semiMajor: A, flattening: A)(implicit fractional : Fractional[A]){
  val flatteningInverse : A = fractional.div(fractional.fromDouble(1.0), flattening)
  val semiMinor : A =  fractional.times( fractional.minus(fractional.fromDouble(1.0) , flattening) , semiMajor)
}

object Ellipsoid{

  def fromSemiMajorAxisandFlattening[A](semiMajor: A, flattening: A)
                                    (implicit fractional : Fractional[A]): Ellipsoid[A] =
    Ellipsoid(semiMajor, flattening)

  def fromSemiMajorAxisAndInverseFlattening[A](semiMajor: A, flatteningInverse: A)
                                           (implicit fractional : Fractional[A]): Ellipsoid[A] = {
    val flattening = fractional.div(fractional.fromDouble(1.0), flatteningInverse)
    Ellipsoid(semiMajor, flattening)
  }

}
