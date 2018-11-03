package resources

import lib.jsonapi.RelationshipResource
import models.ExerciseModel
import play.api.libs.json.{JsObject, Json}

/**
  * [[ExerciseModel]] => JsonApi
  *
  * @param model
  */
case class ExerciseRelationshipResource(model: ExerciseModel) extends RelationshipResource {

  lazy val id: String = model.id.toString

  override lazy val topLevelTag: String = "exercise"

  lazy val data: JsObject =
    Json.obj(
      "id" -> model.id,
      "type" -> model.exercise
    )
}
