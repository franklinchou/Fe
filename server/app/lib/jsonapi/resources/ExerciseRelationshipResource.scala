package lib.jsonapi.resources

import lib.jsonapi.RelationshipResource
import models.ExerciseModel
import models.exercises.Exercise._
import play.api.libs.json.{JsObject, Json}

case class ExerciseRelationshipResource(exerciseModel: ExerciseModel) extends RelationshipResource {

  lazy val topLevelTag: String = "exercise"

  lazy val data: JsObject =
    Json.obj(
      "type" -> exerciseModel.exercise,
      "id" -> exerciseModel.id,
    )
}
