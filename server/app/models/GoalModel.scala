package models

import ai.x.play.json.Jsonx
import play.api.libs.json.OFormat
import values.wrappers.{DateValueWrapper, StringValueWrapper}
import values.{GoalTitle, TargetDate, UUIDValue}

object GoalModel {

  implicit lazy val jsonFormat: OFormat[GoalModel] = Jsonx.formatCaseClass[GoalModel]

}

case class GoalModel(id: StringValueWrapper[UUIDValue],
                     title: StringValueWrapper[GoalTitle],
                     targetDate: DateValueWrapper[TargetDate],
                     sessions: Seq[SessionModel]) {

}
