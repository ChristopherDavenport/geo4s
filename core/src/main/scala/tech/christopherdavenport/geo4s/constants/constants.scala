package tech.christopherdavenport.geo4s

import spire.math.Fractional
import tech.christopherdavenport.geo4s.structures.Ellipsoid

package object constants {

  val RADIUS_OF_EARTH_METERS : Double = 6371008

  def GRS80[A](implicit F: Fractional[A]) : Ellipsoid[A] =
    Ellipsoid.fromSemiMajorAxisAndInverseFlattening[A](F.fromDouble(6378137.0), F.fromDouble(298.257222101))

  def GRS67[A](implicit F: Fractional[A]) : Ellipsoid[A] =
    Ellipsoid.fromSemiMajorAxisAndInverseFlattening(F.fromDouble(6378160.0), F.fromDouble(298.25))

  def ANS [A](implicit F: Fractional[A]) : Ellipsoid[A] =
    Ellipsoid.fromSemiMajorAxisAndInverseFlattening(F.fromDouble(6378160.0), F.fromDouble(298.25))

  def WGS72[A](implicit F: Fractional[A]) : Ellipsoid[A] =
    Ellipsoid.fromSemiMajorAxisAndInverseFlattening(F.fromDouble(6378135.0), F.fromDouble(298.26))

  def WGS84[A](implicit F: Fractional[A]) : Ellipsoid[A] =
    Ellipsoid.fromSemiMajorAxisAndInverseFlattening(F.fromDouble(6378137.0), F.fromDouble(295.257223563))

  def Clarke1858[A](implicit F: Fractional[A]) : Ellipsoid[A] =
    Ellipsoid.fromSemiMajorAxisAndInverseFlattening(F.fromDouble(6378293.645), F.fromDouble(294.26))

  def Clark1880[A](implicit F: Fractional[A]) : Ellipsoid[A] =
    Ellipsoid.fromSemiMajorAxisAndInverseFlattening(F.fromDouble(6378249.145), F.fromDouble(293.465))

  def SphereEllipsoid[A](implicit F: Fractional[A]) : Ellipsoid[A] =
    Ellipsoid.fromSemiMajorAxisandFlattening(F.fromDouble(6371000), F.fromDouble(0.0))

}
