package controllers

import com.google.inject.{Inject, Singleton}
import lib.containers.StringContainer
import models.{AbstractUserId, SessionModel}
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
import resources.{SessionResource, SessionResources}
import services.ReportingService

import scala.concurrent.ExecutionContext


@Singleton
class SessionController @Inject()(cc: ControllerComponents,
                                  rs: ReportingService)
                                 (implicit ec: ExecutionContext) extends AbstractController(cc) {

  def index() = Action.async  { implicit request: Request[AnyContent] =>
    val user = request.headers.get("user").getOrElse("") // TODO Make this work

    rs
      .findAll(StringContainer.apply[AbstractUserId](user))
      .map { models =>
        val json = SessionResources(models).toJsonApi
        Ok(json)
      }
  }


  def create() = Action(parse.tolerantJson) {
    implicit request: Request[JsValue] => {
      val body = request.body
      body.validate[SessionModel]
      Ok
    }
  }



}
