package io.chrisdavenport.gps4s.structures

/**
  * Created by davenpcm on 2/11/17.
  */
sealed trait DistanceUnit

object DistanceUnit {
  object Meter extends DistanceUnit
  object Kilometer extends DistanceUnit
}


