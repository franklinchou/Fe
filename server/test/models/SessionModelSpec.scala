package models

import java.time.LocalDate

import lib.containers.StringContainer
import models.strength.SetModel
import org.scalatest.FunSpec


object SessionModelSpec {

  val date = LocalDate.now

  val mockBenchPress =
    ExerciseModel(
      StringContainer.apply[AbstractModelId]("mock-exercise-1"),
      "BenchPress",
      "",
      "Standard bench press",
      135.00
    )


  // 5 x 5 bench press workout
  val mockBenchWorkout =
    (0 to 5)
      .toList
      .map(sc =>
        SetModel(
          StringContainer.apply[AbstractModelId](s"mock-bench-set $sc"),
          5,
          mockBenchPress
        )
      )

  val mockSession =
    SessionModel(
      StringContainer.apply[AbstractModelId]("mock-session"),
      date,
      mockBenchWorkout
    )

}


import models.SessionModelSpec._


class SessionModelSpec extends FunSpec {

  describe("A work out session") {

    it ("should marshall to json") {
      val q = mockSession
    }

  }

}
