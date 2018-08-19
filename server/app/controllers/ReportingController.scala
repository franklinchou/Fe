package controllers

import com.google.inject.{Inject, Singleton}
import play.api.mvc._


@Singleton
class ReportingController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {


  def index() = Action { implicit request: Request[AnyContent] =>
    Ok
  }

}
