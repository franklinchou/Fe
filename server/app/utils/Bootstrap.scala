package utils

import play.api.Logger
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.libs.json.Json
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.api.DefaultDB
import reactivemongo.api.commands.WriteResult
import reactivemongo.play.json._
import reactivemongo.play.json.collection.JSONCollection

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

object Bootstrap extends App {

  import scala.concurrent.ExecutionContext.Implicits.global

  val appBuilder = new GuiceApplicationBuilder()
  val app = appBuilder.build()
  val injector = app.injector

  val reactiveMongoApi = injector.instanceOf[ReactiveMongoApi]


  private val db: Future[DefaultDB] = {
    val db = reactiveMongoApi.database
    val dbName = Await.result(db.map(_.name), Duration.Inf)
    assert(dbName == "fe") // should connect to the default database
    db
  }

  private def populate: Future[WriteResult] = {

    println(mockSessionJson)

    db
      .map(_.collection[JSONCollection]("exercise"))
      .flatMap(_.insert(mockSessionJson))
  }


  private def remove(id: String): Future[WriteResult] = {

    val selector = Json.obj("id" -> "")

    db
      .map(_.collection[JSONCollection]("exercise"))
      .flatMap(_.remove(selector))
  }


  /**
    * Usage:
    *
    * From the `server` directory issue:
    * `sbt "runMain utils.Bootstrap"`  Note: `run-main` is deprecated.
    */
  if (args.isEmpty) {
    Logger.info("Populating mock data...")
    populate
  } else {
    args(0) match {
      case "delete" =>
      case _ =>
        Logger.warn(s"Argument ${args(0)} invalid. Stopping.")
        app.stop()
    }
  }

  app.stop()

}
