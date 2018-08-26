package controllers

import com.google.inject.{Inject, Singleton}
import models.SessionModel
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}


@Singleton
class LoggingController @Inject()(cc: ControllerComponents)
                                 (implicit ec: ExecutionContext) extends AbstractController(cc) {

  def index() = Action.async  { implicit request: Request[AnyContent] =>
    val userHeader = request.headers.get("user").getOrElse("")
    val user = Json.parse(userHeader).validate[String]


    Future { BadRequest }  // TODO Implement
  }

}
