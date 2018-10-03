package lib.jsonapi.resources

import lib.jsonapi.CompoundDataResource
import models.SessionModel
import play.api.libs.json.{JsObject, Json}

case class SessionResource(sessionModel: SessionModel) extends CompoundDataResource {

  lazy val `type`: String = "exercise-session"

  lazy val id: String = sessionModel.id.value

  lazy val attributes: Option[JsObject] = {
    val json =
      Json.obj(
        "date" -> sessionModel.date.toString
      )
    Some(json)
  }

  lazy val relationships: Option[JsObject] = None

  lazy val links: Option[JsObject] = None

  lazy val included: List[JsObject] =
    sessionModel
      .sets
      .map(s => Resource2IncludedResource(SetResource.apply(s)))
      .map(sr => sr.toJsonApi)

}
