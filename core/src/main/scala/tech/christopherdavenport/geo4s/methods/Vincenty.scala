package tech.christopherdavenport.geo4s.methods

import tech.christopherdavenport.geo4s.structures.Ellipsoid
import spire.algebra._
import spire.math._
import spire.implicits._

import scala.annotation.tailrec

object Vincenty {

  case class Convergence[A](acceptable: A)(implicit fractional: Fractional[A])

  // ϕ
  // Calculations and values match Vincenty original formulas
  def pure[A](lat1: A, lon1: A, lat2: A, lon2: A)
             (ellipsoid: Ellipsoid[A])
             (implicit T: Trig[A], F : Fractional[A]): A = {

    // Major Semiaxes
    val a : A = ellipsoid.semiMajor
    // Minor Semiaxes
    val b : A = ellipsoid.semiMinor
    // Flatenning which should equal (a - b)/a
    val f : A = ellipsoid.flattening

    // Geodetic Latitude, positive north of the equator
    val phi1 : A = T.toRadians(lat1)
    val phi2 : A = T.toRadians(lat2)

    // Difference in longitude, positive east
    val lambda1 = T.toRadians(lon1)
    val lambda2 = T.toRadians(lon2)

    val U1 = atan((1 - f) * tan(phi1))
    val U2 = atan((1 - f) * tan(phi2))
    val omega = lambda2 - lambda1

    case class LambdaResult(A: A, B: A, sigma: A,  deltaSigma: A, lambda: A)

    @tailrec
    def calcLambda(initLambda: A, count: Int = 0)(omega: A, a: A, b: A, f: A, U1: A, U2: A, convergeThreshold : A = F.fromDouble(0.0000000000001)): LambdaResult = {

      // Equation 14
      val sigma = T.atan2(
        sqrt( F.pow(cos(U2) * sin(initLambda), 2) + F.pow( (cos(U1) * sin(U2)) - (sin(U1) * cos(U2) * cos(initLambda)) , 2 )),
        sin(U1) * sin(U2) + ( cos(U1) * cos(U2) * cos(initLambda))
        )


      val alpha = asin(cos(U1) * cos(U2) * sin(initLambda) / sin(sigma))

      val u2 = F.pow(cos(sigma), 2) * ((F.pow(a, 2) - F.pow(b, 2)) / F.pow(b, 2))

      // Equation 3
      val A = 1.0 + u2 / 16384 * (4096 + u2 * (-768 + u2 * (320 - 175*u2)))
      // Equation 4
      val B = ( u2 / 1024 ) * (256 + u2 * (-128 + u2 * (74 - 47*u2)))

      // Equation5
      val cos2sigmam = cos(sigma) - 2 * sin(U1) * sin(U2) / F.pow(cos(alpha), 2)
      // 18
      val cos2sigmam2 = F.pow(cos2sigmam, 2)

      // Equation 6
      val deltaSigma =
        B * sin(sigma) * (
          cos2sigmam + B / 4 * (
              cos(sigma) * (-1 + 2* cos2sigmam2) - B /
              6 * cos2sigmam * (-3 + 4 * F.pow(sin(sigma), 2)) * (-3 + 4 * cos2sigmam2)
            )
        )

      // Equation 10
      val C = f / 16 * F.pow(cos(alpha), 2) * ( 4 + f * (4 - 3 * F.pow(cos(alpha), 2)))

      // Equation 11
      val lambda = omega + (1 - C) * f * sin(alpha) * (sigma + C * sin(sigma) * (cos2sigmam + C * cos(sigma) * (-1 + 2 * cos2sigmam2)))

      val change = if (lambda != initLambda) F.abs((lambda - initLambda) / lambda) else F.fromDouble(0.0)
      if (change < convergeThreshold && count > 0 && count <= 1000) {
        LambdaResult(A, B, sigma, deltaSigma, lambda)
      } else {
        calcLambda(lambda, count + 1)(omega, a, b, f, U1, U2, convergeThreshold)
      }
    }

    val lambdaResult = calcLambda(omega)(omega, a, b, f, U1, U2)

    // Length of the Geodesic
    // Equation 19
    val s = b * lambdaResult.A * (lambdaResult.sigma - lambdaResult.deltaSigma)

    s


    // Azimuths of the geodesic
    // azimuth = the direction of a celestial object from the observer,
    // expressed as the angular distance from the north or south point
    // of the horizon to the point at which a vertical circle passing
    // through the object intersects the horizon.
//
//    // clockwise from the north
//    val α1 : A = ???
//    // in the direction of P1 P2
//    val α2 : A = ???
//    // azimuth of the geodesic at the equator
//    val α : A = ???
//
//    // cos^2(alpha*(a^2 - b^2)/b^2)
//    val uSquared : A = F.pow( cos(α * ((a*a) - (b*b)) / (b*b)), 2 )
//    // reduced latitude, defined by tan U == (1 - f) tan Փ
//
//    // difference in longitude on an auxiliary sphere
//    val λ = L2 - L1
//    // angular distance P1P2 on the sphere
//    val σ : A = 0
//
//    // angular distance on the sphere from the equator to P1
//    val σ1 : A = ???
//
//    // angular distance on the sphere from the equator to the midpoint of the line
//    val σm : A = ???
//
//    def repeat(sigma: A, )
//    val A = 1 + uSquared / 256 * (64 + uSquared * (-12 + 5 * uSquared))
//    val B = (uSquared / 512) * (128 + uSquared * (-64 + 37 * uSquared))
//    val deltaSigma =
//      B * sin(σ * ( cos( 2* σm ) + ((1/4) * B * ( cos(-1 + 2 * cos(2 * σm)) * cos(-1 + 2 * cos(2 * σm))))))






  }

}
