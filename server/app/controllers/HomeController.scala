package controllers

import javax.inject.{Inject, Singleton}
import play.api.Configuration
import play.api.libs.json.Json
import play.api.mvc._
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.api.MongoConnection

@Singleton
class HomeController @Inject()(cc: ControllerComponents,
                               config: Configuration,
                               rma: ReactiveMongoApi) extends AbstractController(cc) {

  def health() = Action { implicit request: Request[AnyContent] =>

    val mongo = if (rma.connection.active) "online" else "offline"

    val info =
      Json.obj(
        "application" -> config.get[String]("app.name"),
        "description" -> "Weight lifting tracker app",
        "environment" -> config.get[String]("app.env"),
        "mongo" -> mongo
      )

    Ok(info)
  }
}
