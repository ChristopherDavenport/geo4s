package io.chrisdavenport.gps4s.coordinate

import cats._
/**
  * Created by davenpcm on 2/11/17.
  */
case class Minute(n : Int)

object Minute {
  implicit val minuteEq : Eq[Minute] = Eq.fromUniversalEquals[Minute]
  implicit val minuteShow: Show[Minute] = Show.fromToString[Minute]


}