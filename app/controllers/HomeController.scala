package controllers

import akka.util.ByteString
import play.api.http.HttpEntity
import javax.inject._
import play.api.mvc._


@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def health() = Action { implicit request: Request[AnyContent] =>

    val healthMessage = ByteString("I'm alive")

    Result(
      header = ResponseHeader(200, Map.empty),
      body = HttpEntity.Strict(healthMessage, Some("text/html"))
    )
  }
}
