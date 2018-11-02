package utils

import java.time.LocalDate

import dao.mongo.SessionRepo
import models.exercises.BenchPress
import models.strength.SetModel
import models.{ExerciseModel, SessionModel}
import play.api.inject.guice.GuiceApplicationBuilder
import play.modules.reactivemongo.ReactiveMongoApi

object LoadTestData extends App {

  val application = new GuiceApplicationBuilder().build

  val injector = application.injector

  val reactiveMongoApi = application.injector.instanceOf[ReactiveMongoApi]

  val sessionRepo = application.injector.instanceOf[SessionRepo]

  val mockExerciseModel =
    ExerciseModel.apply(
      BenchPress,
      "Bench press",
      "Close-grip bench press",
      135.00
    )

  val mockSetModel =
    SetModel.apply(
      1,
      mockExerciseModel
    )

  val mockSessionModel =
    SessionModel.apply(
      LocalDate.now,
      List(mockSetModel)
    )

  sessionRepo.insert(mockSessionModel)

  application.stop()

}
