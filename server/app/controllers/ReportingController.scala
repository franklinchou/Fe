package controllers

import com.google.inject.{Inject, Singleton}
import lib.containers.StringContainer
import models.AbstractUserId
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
import services.ReportingService

import scala.concurrent.{ExecutionContext, Future}


@Singleton
class ReportingController @Inject()(cc: ControllerComponents,
                                    rs: ReportingService)
                                   (implicit ec: ExecutionContext) extends AbstractController(cc) {

  def index() = Action.async  { implicit request: Request[AnyContent] =>
    val user = request.headers.get("user").getOrElse("")

    if (user == "") {
      Future { Unauthorized }

      // TODO Should hit a user service?
    } else {
      rs
        .findAll(StringContainer.apply[AbstractUserId](user))
        .map { model =>
          val json = Json.toJson(model)
          Ok(json)
        }
    }
  }


  def create() = Action(parse.tolerantJson) {
    implicit request: Request[JsValue] => {
      val body = request.body
      
    }
  }



}
