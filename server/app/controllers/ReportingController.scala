package controllers

import com.google.inject.{Inject, Singleton}
import play.api.mvc._
import services.GenericService


@Singleton
class ReportingController @Inject()(cc: ControllerComponents,
                                    service: GenericService) extends AbstractController(cc) {




  def index() = Action { implicit request: Request[AnyContent] =>
    Ok
  }

}
