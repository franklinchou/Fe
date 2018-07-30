package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.test.Helpers._
import play.api.test._

class HomeControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "Home controller" should {
    "have a health endpoint" in {
      val health = FakeRequest("GET", "/health")
      val response = route(app, health).get
      assert(status(response) == 200)
    }
  }

}
