package lib.jsonapi.resources

import lib.jsonapi.DataResource
import models.ExerciseModel
import play.api.libs.json.{JsObject, Json}

object ExerciseResource {


}

case class ExerciseResource(exerciseModel: ExerciseModel) extends DataResource {

  lazy val `type`: String = "exercise"

  lazy val id: String = exerciseModel.id.value

  lazy val attributes: Option[JsObject] = {
    val json =
      Json.obj(
        "exercise" -> exerciseModel.exercise.toString,
        "description"-> exerciseModel.description,
        "variation" -> exerciseModel.variation,
        "weight" -> exerciseModel.weight
      )

    Some(json)
  }

  lazy val relationships: Option[JsObject] = None

  lazy val links: Option[JsObject] = None

  lazy val meta: Option[JsObject] = None

}
