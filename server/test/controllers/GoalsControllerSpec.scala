package controllers

import org.scalatest.concurrent.ScalaFutures
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.http.HttpVerbs.GET
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.libs.json.Json
import play.api.mvc.Headers
import play.api.test.{FakeRequest, Helpers, Injecting}
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.api.DefaultDB
import utils.FileReader

/**
  * Helpers is passing in an implicit timeout for contentType
  */
import play.api.test.Helpers._

import scala.concurrent.{ExecutionContext, Future}

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

    "write to the database" in {

      val body = FileReader.loadToString("test.json")
      val bodyJson = Json.parse(body)

      val request =
        FakeRequest(
          GET,
          s"/goals",
          headers = Headers("Content-Type" -> "application/json"),
          body = bodyJson
        )

      val c = testController.createFromJson().apply(request)

      status(c) mustBe CREATED
    }

  }


}
