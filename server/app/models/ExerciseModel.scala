package models

import ai.x.play.json.Jsonx
import lib.containers.StringContainer
import models.exercises.Exercise
import play.api.libs.json.OFormat


object ExerciseModel {

  /**
    * Json format
    */
  implicit lazy val jsFormat: OFormat[ExerciseModel] = Jsonx.formatCaseClass[ExerciseModel]

}


case class ExerciseModel(id: StringContainer[AbstractModelId],
                         // exercise: Exercise,
                         exercise: String,  // TODO Use Exercise model
                         description: String,
                         variation: String,
                         weight: Double
                         /* duration: Option[???] */) extends AbstractModel