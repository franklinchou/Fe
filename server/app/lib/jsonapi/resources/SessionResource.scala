package lib.jsonapi.resources

import jsonapi.DataResource
import models.SessionModel
import play.api.libs.json.{JsObject, Json}

case class SessionResource(sessionModel: SessionModel) extends DataResource {

  val `type`: String = "exercise-session"

  val id: String = sessionModel.id.value

  val attributes: Option[JsObject] = {
    val json =
      Json.obj(
        "date" -> sessionModel.date.toString,
        "sets" -> sessionModel.sets
      )
    Some(json)
  }

  val relationships: Option[JsObject] = None

  val links: Option[JsObject] = None

  val meta: Option[JsObject] = None

}
