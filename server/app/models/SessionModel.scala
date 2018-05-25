package models

import ai.x.play.json.Jsonx
import play.api.libs.json.OFormat
import values.wrappers.{DateValueWrapper, IntValueWrapper}
import values.{SessionDateValue, SessionNumberValue}


object SessionModel {

  implicit lazy val jsonFormat: OFormat[SessionModel] = Jsonx.formatCaseClass[SessionModel]

}


case class SessionModel(sessionNumber: IntValueWrapper[SessionNumberValue],
                        date: DateValueWrapper[SessionDateValue],
                        exercises: Seq[ExerciseModel])