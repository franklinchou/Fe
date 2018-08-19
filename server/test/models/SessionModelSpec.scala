package models

import java.time.LocalDate

import lib.containers.StringContainer
import models.exercises.BenchPress
import models.strength.SetModel
import org.scalatest.FunSpec
import play.api.libs.json.Json


object SessionModelSpec {

  val date = LocalDate.now

  val mockBenchPress =
    ExerciseModel(
      StringContainer.apply[AbstractModelId]("mock-exercise-1"),
      "BenchPress", // TODO Use Exercise model
      // BenchPress,
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

    it("should marshall to json") {
      val actual = Json.toJson(mockSession)

      val expected =
        s"""
           |{
           |  "id" : "mock-session",
           |  "date" : "$date",
           |  "sets" : [ {
           |    "id" : "mock-bench-set 0",
           |    "multiplier" : 5,
           |    "exercise" : {
           |      "id" : "mock-exercise-1",
           |      "exercise" : "BenchPress",
           |      "description" : "",
           |      "variation" : "Standard bench press",
           |      "weight" : 135
           |    }
           |  }, {
           |    "id" : "mock-bench-set 1",
           |    "multiplier" : 5,
           |    "exercise" : {
           |      "id" : "mock-exercise-1",
           |      "exercise" : "BenchPress",
           |      "description" : "",
           |      "variation" : "Standard bench press",
           |      "weight" : 135
           |    }
           |  }, {
           |    "id" : "mock-bench-set 2",
           |    "multiplier" : 5,
           |    "exercise" : {
           |      "id" : "mock-exercise-1",
           |      "exercise" : "BenchPress",
           |      "description" : "",
           |      "variation" : "Standard bench press",
           |      "weight" : 135
           |    }
           |  }, {
           |    "id" : "mock-bench-set 3",
           |    "multiplier" : 5,
           |    "exercise" : {
           |      "id" : "mock-exercise-1",
           |      "exercise" : "BenchPress",
           |      "description" : "",
           |      "variation" : "Standard bench press",
           |      "weight" : 135
           |    }
           |  }, {
           |    "id" : "mock-bench-set 4",
           |    "multiplier" : 5,
           |    "exercise" : {
           |      "id" : "mock-exercise-1",
           |      "exercise" : "BenchPress",
           |      "description" : "",
           |      "variation" : "Standard bench press",
           |      "weight" : 135
           |    }
           |  }, {
           |    "id" : "mock-bench-set 5",
           |    "multiplier" : 5,
           |    "exercise" : {
           |      "id" : "mock-exercise-1",
           |      "exercise" : "BenchPress",
           |      "description" : "",
           |      "variation" : "Standard bench press",
           |      "weight" : 135
           |    }
           |  } ]
           |}
        """.stripMargin

      assert(Json.parse(expected) == actual)
    }

  }

}
