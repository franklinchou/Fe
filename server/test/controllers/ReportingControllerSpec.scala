package controllers

import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.test.Helpers._
import play.api.test._
import controllers.Headers._

class ReportingControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "A Reporting Controller" should {

    s"$UNAUTHORIZED if the user header is empty" in {
      val request1 = FakeRequest("GET", "/logging")
      val request2 = FakeRequest("GET", "/logging", NO_USER, "")

      val method1 = route(app, request1).get
      assert(status(method1) == UNAUTHORIZED)

      val method2 = route(app, request2).get
      assert(status(method2) == UNAUTHORIZED)
    }

    // Testing with proper user header

  }

}
