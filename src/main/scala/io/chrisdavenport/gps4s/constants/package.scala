package io.chrisdavenport.gps4s

import io.chrisdavenport.gps4s.structures.Ellipsoid
//import io.chrisdavenport.gps4s.structures.DistanceUnit._
import spire.math._

/**
  * Created by davenpcm on 2/9/17.
  */
package object constants {

  val RADIUS_OF_EARTH_METERS : Double = 6371008


  def GRS80[A](implicit F: Fractional[A]) : Ellipsoid[A] =
    Ellipsoid.fromSemiMajorAxisAndInverseFlattening[A](F.fromDouble(6378137.0), F.fromDouble(298.257222101))

//  def GRS67 : Ellipsoid =
//
//    Ellipsoid.fromSemiMajorAxisAndInverseFlattening(6378160.0, 298.25)
//
//  def ANS : Ellipsoid =
//    Ellipsoid.fromSemiMajorAxisAndInverseFlattening(6378160.0, 298.25)
//
//  def WGS72 : Ellipsoid =
//    Ellipsoid.fromSemiMajorAxisAndInverseFlattening(6378135.0, 298.26)

  def WGS84[A](implicit F: Fractional[A]) : Ellipsoid[A] =
    Ellipsoid.fromSemiMajorAxisAndInverseFlattening(F.fromDouble(6378137.0), F.fromDouble(295.257223563))

//  def Clarke1858 : Ellipsoid =
//    Ellipsoid.fromSemiMajorAxisAndInverseFlattening(6378293.645, 294.26)
//
//  def Clark1880 : Ellipsoid =
//    Ellipsoid.fromSemiMajorAxisAndInverseFlattening(6378249.145, 293.465)

  def SphereEllipsoid[A](implicit F: Fractional[A]) : Ellipsoid[A] =
    Ellipsoid.fromSemiMajorAxisandFlattening(F.fromDouble(6371000), F.fromDouble(0.0))

}
