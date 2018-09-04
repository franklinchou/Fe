package models

import java.time.LocalDate

import ai.x.play.json.Jsonx
import lib.containers.StringContainer
import models.strength.SetModel
import play.api.libs.json.OFormat


object ExerciseSessionModel {

  /**
    * Json format
    */
  implicit lazy val jsFormat: OFormat[ExerciseSessionModel] = Jsonx.formatCaseClass[ExerciseSessionModel]

}

case class ExerciseSessionModel(id: StringContainer[AbstractModelId],
                                date: LocalDate,
                                sets: List[SetModel]) extends AbstractModel