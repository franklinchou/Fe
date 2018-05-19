package models

import ai.x.play.json.Jsonx
import play.api.libs.json.OFormat
import values._
import values.wrappers.{BigDecimalValueWrapper, IntValueWrapper, StringValueWrapper}

object ExerciseModel {

  implicit lazy val jsonFormat: OFormat[ExerciseModel] = Jsonx.formatCaseClass[ExerciseModel]

}

case class ExerciseModel(lift: StringValueWrapper[ExerciseName],
                         weight: BigDecimalValueWrapper[Weight],
                         weightUnit: StringValueWrapper[WeightUnit],
                         repCount: IntValueWrapper[RepCount],
                         setCount: IntValueWrapper[SetCount]) {

}
