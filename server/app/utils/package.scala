import java.time.LocalDate

import lib.containers.StringContainer
import models.exercises.DeadLift
import models.strength.SetModel
import models.{AbstractModelId, ExerciseModel, ExerciseSessionModel}
import play.api.libs.json.{JsObject, Json}

package object utils {

  val mockDeadLiftModel =
    ExerciseModel(
      StringContainer.apply[AbstractModelId]("mock-exercise-2"),
      DeadLift,
      "",
      "Standard dead lift",
      205.00
    )

  val mockDeadLiftSet =
    SetModel(
      StringContainer.apply[AbstractModelId]("mock-squat-set"),
      1,
      mockDeadLiftModel
    )

  val mockSessionModel =
    ExerciseSessionModel(
      StringContainer.apply[AbstractModelId]("mock-session"),
      LocalDate.now(),
      List(mockDeadLiftSet)
    )

  val mockSessionJson: JsObject = Json.toJsObject[ExerciseSessionModel](mockSessionModel)

}
