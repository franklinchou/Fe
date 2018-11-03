package controllers

import controllers.Headers._
import lib.containers.StringContainer
import models.{AbstractUserId, SessionModel}
import org.mockito.Mockito.when
import org.scalatest.mockito.MockitoSugar
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.test.Helpers._
import play.api.test._
import services.ReportingService

import scala.concurrent.Future

class SessionControllerSpec
  extends PlaySpec with MockitoSugar with GuiceOneAppPerTest with Injecting {

  import scala.concurrent.ExecutionContext.Implicits.global

  "A Reporting Controller" should {

    s"$UNAUTHORIZED if the user header is empty" in {
      val request1 = FakeRequest(GET, "/sessions")
      val request2 = FakeRequest(GET, "/sessions", NO_USER, "")

      val method1 = route(app, request1).get
      assert(status(method1) == UNAUTHORIZED)

      val method2 = route(app, request2).get
      assert(status(method2) == UNAUTHORIZED)
    }

    // Testing with proper user header
    s"$OK if the proper user header is given" ignore {

      /**
        * For some reason using the FakeRequest.apply(method, endpoint, headers, body)
        * creates object with signature FakeRequest[String] & compile time error:
        *
        * "could not find implicit value for parameter mat: akka.stream.Materializer"
        */
      val request =
        FakeRequest(GET, "/sessions")
          .withHeaders(TEST_USER)

      val rs = mock[ReportingService]
      val mockSessionController = new SessionController(stubControllerComponents(), rs)

      val user = TEST_USER.get("user").getOrElse("")
      val u = StringContainer.apply[AbstractUserId](user)
      when(rs.findAll(u)).thenReturn(Future {List.empty[SessionModel]} )

      val method = mockSessionController.index()(request)
      assert(status(method) == OK)
    }

  }

}
