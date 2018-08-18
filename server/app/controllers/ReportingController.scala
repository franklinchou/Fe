package controllers

import com.google.inject.Inject
import play.api.mvc._

class ReportingController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {


  def index() = Action { implicit request: Request[AnyContent] =>
    Ok
  }

}
