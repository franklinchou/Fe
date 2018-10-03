package models

import ai.x.play.json.Jsonx
import lib.containers.StringContainer
import models.exercises._
import play.api.libs.json._

object ExerciseModel {

  /**
    * Json format
    */
  implicit lazy val jsFormat: OFormat[ExerciseModel] = Jsonx.formatCaseClass[ExerciseModel]

}

case class ExerciseModel(id: StringContainer[AbstractModelId],
                         exercise: Exercise,
                         description: String,
                         variation: String,
                         weight: Double
                         /* duration: Option[???] */) extends AbstractModel