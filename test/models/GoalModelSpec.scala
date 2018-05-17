package models

import java.time.LocalDate
import java.util.UUID

import models.ExerciseSpec.{benchPress, squat}
import org.scalatest.FunSpec
import play.api.libs.json.Json
import values._
import values.wrappers.{DateValueWrapper, IntValueWrapper, StringValueWrapper}

class GoalModelSpec extends FunSpec {

  val today: LocalDate = LocalDate.now()

  val targetDate: LocalDate = today.plusDays(5)

  val exercises: Seq[ExerciseModel] = Seq(benchPress, squat)

  val goalMockUUID: String = UUID.randomUUID().toString

  val sessionMock =
    SessionModel(
      sessionNumber = IntValueWrapper[SessionNumber](1),
      date = DateValueWrapper[SessionDate](today),
      exercises = exercises
    )

  val goalMock =
    GoalModel(
      id = StringValueWrapper[UUIDValue](goalMockUUID),
      title = StringValueWrapper[GoalTitle]("A mock goal model"),
      targetDate = DateValueWrapper[TargetDate](targetDate),
      sessions = Seq(sessionMock)
    )

  describe("A goal model") {
    it("should have a UUID") {
      assert(goalMock.id == StringValueWrapper[UUIDValue](goalMockUUID))
    }
    it("should be json serializable") {
      val json = Json.toJson(goalMock)
      println(Json.prettyPrint(json))
    }
  }

}
