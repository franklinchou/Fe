package controllers

import java.time.LocalDate

import models._
import org.scalatest.concurrent.ScalaFutures
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.http.HttpVerbs.GET
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.libs.json.{JsObject, JsValue, Json}
import play.api.mvc.{Headers, Result}
import play.api.test.{FakeRequest, Injecting}
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.api.DefaultDB
import values._
import values.wrappers.{DateValueWrapper, IntValueWrapper, StringValueWrapper}

import scala.concurrent.{ExecutionContext, Future}

/**
  * `Helpers` is passing in an implicit timeout for contentType
  */
import play.api.test.Helpers._



object GoalsControllerSpec {

  protected def createRecord(recordToInsert: JsObject, gc: GoalsController): Future[Result] = {

    val request =
      FakeRequest(
        GET,
        s"/goals",
        headers = Headers("Content-Type" -> "application/json"),
        body = recordToInsert
      )

    gc.createFromJson().apply(request)
  }

}


class GoalsControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "GoalsController" should {

    val appBuilder = new GuiceApplicationBuilder().build

    /**
      * Used by `map` in Future.
      */
    implicit val ec: ExecutionContext = appBuilder.injector.instanceOf[ExecutionContext]

    val database: Future[DefaultDB] = appBuilder.injector.instanceOf[ReactiveMongoApi].database

    "access the correct database" in {
      val databaseName = database.map(_.name)

      ScalaFutures.whenReady(databaseName) { s =>
        assert(s == "weights")
      }
    }


    val testController = appBuilder.injector.instanceOf[GoalsController]

    val today: LocalDate = LocalDate.now()

    val targetDate: LocalDate = today.plusDays(5)

    val exercises =
      Seq(ExerciseModelSpec.benchPress, ExerciseModelSpec.squat)

    val sessionMock =
      SessionModel(
        sessionNumber = IntValueWrapper[SessionNumberValue](1),
        date = DateValueWrapper[SessionDateValue](today),
        exercises = exercises
      )


    val mockModel =
      GoalModel.apply(
        id = StringValueWrapper[UUIDValue]("2f742976-0223-45a4-97b3-928e855fcb7a"),
        title = StringValueWrapper[GoalTitle]("A mock goal model"),
        targetDate = DateValueWrapper[TargetDate](targetDate),
        sessions = Seq(sessionMock)
      )



    val mockRecordJson = Json.toJsObject(mockModel)
    // val noId = "id" -> Json.parse("")

    // val mockRecordId = (mockRecordJson \ "id").getOrElse(JsObject(Map(noId)))

    "write a mock record to database" in {
      val record = GoalsControllerSpec.createRecord(mockRecordJson, testController)
      status(record) mustBe CREATED
    }


//    "find that mock record in database" in {
//      val query = JsObject(Map("id" -> mockRecordId))
//      val found = testController.find(query)
//
//      println(found)
//
//    }

  }


}
