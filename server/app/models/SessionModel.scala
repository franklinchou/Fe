package models

import java.time.LocalDate

import ai.x.play.json.Jsonx
import containers.StringContainer
import play.api.libs.json.OFormat


object SessionModel {

  /**
    * Json format
    */
  implicit lazy val jsFormat: OFormat[SessionModel] = Jsonx.formatCaseClass[SessionModel]

}

case class SessionModel(id: StringContainer[AbstractModelId],
                        date: LocalDate,
                        exercises: List[ExerciseModel]) extends AbstractModel