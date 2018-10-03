package lib.jsonapi.resources

import lib.jsonapi.{DataResource, ExerciseJsonApi}
import models.strength.SetModel
import play.api.libs.json.{JsObject, Json}

case class SetResource(setModel: SetModel) extends DataResource {

  lazy val `type`: String = "set"

  lazy val id: String = setModel.id.value

  lazy val attributes: Option[JsObject] = {

    val em = setModel.exercise

    val json =
      Json.obj(
        "multiplier" -> setModel.multiplier,
        "exercise" -> ExerciseJsonApi(em.exercise),
        "description" -> em.description,
        "variation" -> em.variation,
        "weight" -> em.weight
      )
    Some(json)
  }

  lazy val relationships: Option[JsObject] = {
    val json = ExerciseRelationshipResource.apply(setModel.exercise).toJsonApi
    Some(json)
  }

  lazy val links: Option[JsObject] = None

  lazy val meta: Option[JsObject] = None

}
