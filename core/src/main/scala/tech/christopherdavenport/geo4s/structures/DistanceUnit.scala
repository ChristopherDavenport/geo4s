package tech.christopherdavenport.geo4s.structures

sealed trait DistanceUnit

object DistanceUnit {
  object Meter extends DistanceUnit
  object Kilometer extends DistanceUnit
}


