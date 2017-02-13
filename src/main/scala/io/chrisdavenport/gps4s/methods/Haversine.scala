package io.chrisdavenport.gps4s.methods

//import scala.math._
import io.chrisdavenport.gps4s.constants._
import io.chrisdavenport.gps4s.coordinate.Coordinate

//import spire.algebra._   // provides algebraic type classes
import spire.math._      // provides functions, types, and type classes
import spire.implicits._ // provides infix operators, instances and conversions

/**
  * Created by davenpcm on 2/9/17.
  */
object Haversine {

  def pure(lat1:Double, lon1:Double, lat2:Double, lon2:Double)(radiusOfSphere: Double) : Double ={
    val dLat=(lat2 - lat1).toRadians
    val dLon=(lon2 - lon1).toRadians
    val a = pow(sin(dLat/2),2) + pow(sin(dLon/2),2) * cos(lat1.toRadians) * cos(lat2.toRadians)
    val c = 2 * asin(sqrt(a))
    radiusOfSphere * c
  }

  def unitSphere(lat1:Double, lon1:Double, lat2:Double, lon2:Double): Double = {
    pure(lat1, lon1, lat2, lon2)(1D)
  }

  def unitSphere(coordinate1: Coordinate, coordinate2: Coordinate): Double = {
    unitSphere(coordinate1.latitude, coordinate1.longitude, coordinate2.latitude, coordinate2.longitude)
  }

  def earth(lat1:Double, lon1:Double, lat2:Double, lon2:Double): Double = {
    pure(lat1, lon1, lat2, lon2)(RADIUS_OF_EARTH_METERS)
  }

  def earth(coordinate1: Coordinate, coordinate2: Coordinate): Double = {
    earth(coordinate1.latitude, coordinate1.longitude, coordinate2.latitude, coordinate2.longitude)
  }

}
