package lib.jsonapi.resources

import lib.jsonapi.DataResource
import models.strength.SetModel
import play.api.libs.json.{JsObject, Json}

case class SetResource(setModel: SetModel) extends DataResource {

  lazy val `type`: String = "set"

  lazy val id: String = setModel.id.value

  lazy val attributes: Option[JsObject] = {
    val json =
      Json.obj(
        "multiplier" -> setModel.multiplier,
        "exercise" -> setModel.exercise.exercise.toString
      )
    Some(json)
  }

  lazy val relationships: Option[JsObject] = None

  lazy val links: Option[JsObject] = None

  lazy val meta: Option[JsObject] = None

}
