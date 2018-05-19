package models

import values.wrappers.{BigDecimalValueWrapper, IntValueWrapper, StringValueWrapper}
import values._

object ExerciseSpec {

  val benchPress =
    ExerciseModel(
      lift = StringValueWrapper[ExerciseName]("Bench Press"),
      weight = BigDecimalValueWrapper[Weight](BigDecimal(205.00)),
      weightUnit = StringValueWrapper[WeightUnit]("LBS"),
      repCount = IntValueWrapper[RepCount](3),
      setCount = IntValueWrapper[SetCount](8)
    )

  val squat =
    ExerciseModel(
      lift = StringValueWrapper[ExerciseName]("Squat"),
      weight = BigDecimalValueWrapper[Weight](BigDecimal(235.00)),
      weightUnit = StringValueWrapper[WeightUnit]("LBS"),
      repCount = IntValueWrapper[RepCount](3),
      setCount = IntValueWrapper[SetCount](5)
    )
}
