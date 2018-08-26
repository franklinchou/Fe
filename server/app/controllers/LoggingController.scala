package controllers

import com.google.inject.{Inject, Singleton}
import models.SessionModel
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}


@Singleton
class LoggingController @Inject()(cc: ControllerComponents)
                                 (implicit ec: ExecutionContext) extends AbstractController(cc) {

  def index()(implicit request: Request[_]): Future[Seq[SessionModel]] = {
    val userHeader = request.headers.get("user").getOrElse("")
    val user = Json.parse(userHeader).validate[String]


    Future(Seq.empty[SessionModel])
  }

}
