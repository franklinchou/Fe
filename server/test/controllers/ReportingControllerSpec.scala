package controllers

import akka.stream.ActorMaterializer
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.test.Helpers._
import play.api.test._
import controllers.Headers._
import lib.containers.StringContainer
import models.{AbstractUserId, ExerciseSessionModel}
import org.scalatest.mockito.MockitoSugar
import services.ReportingService
import org.mockito.Mockito.when
import play.api.libs.json.Json
import play.api.mvc.AnyContent

import scala.concurrent.Future

class ReportingControllerSpec
  extends PlaySpec with MockitoSugar with GuiceOneAppPerTest with Injecting {

  import scala.concurrent.ExecutionContext.Implicits.global

  "A Reporting Controller" should {

    s"$UNAUTHORIZED if the user header is empty" in {
      val request1 = FakeRequest(GET, "/logging")
      val request2 = FakeRequest(GET, "/logging", NO_USER, "")

      val method1 = route(app, request1).get
      assert(status(method1) == UNAUTHORIZED)

      val method2 = route(app, request2).get
      assert(status(method2) == UNAUTHORIZED)
    }

    // Testing with proper user header
    s"$OK if the proper user header is given" in {

      /**
        * For some reason using the FakeRequest.apply(method, endpoint, headers, body)
        * creates object with signature FakeRequest[String] & compile time error:
        *
        * "could not find implicit value for parameter mat: akka.stream.Materializer"
        */
      val request =
        FakeRequest(GET, "/logging")
          .withHeaders(TEST_USER)

      val rs = mock[ReportingService]
      val mockReportingController = new ReportingController(stubControllerComponents(), rs)

      val user = TEST_USER.get("user").getOrElse("")
      val u = StringContainer.apply[AbstractUserId](user)
      when(rs.findAll(u)).thenReturn(Future {List.empty[ExerciseSessionModel]} )

      val method = mockReportingController.index()(request)
      assert(status(method) == OK)
    }

  }

}
