package controllers

import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.test.Helpers._
import play.api.test._

class ReportingControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "A Reporting Controller" should {

    s"$UNAUTHORIZED if the user header is empty" in {
      val request = FakeRequest("GET", "/logging")

      val method = route(app, request).get
      assert(status(method) == UNAUTHORIZED)
    }

    // Testing with proper user header

  }

}
