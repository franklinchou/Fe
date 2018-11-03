package utils

import java.time.LocalDate

import dao.mongo.SessionRepo
import models.exercises.BenchPress
import models.strength.SetModel
import models.{ExerciseModel, SessionModel}
import play.api.inject.guice.GuiceApplicationBuilder
import play.modules.reactivemongo.ReactiveMongoApi

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object LoadTestData extends App {

  import scala.concurrent.ExecutionContext.Implicits.global

  val application = new GuiceApplicationBuilder().build

  val injector = application.injector

  val _ = injector.instanceOf[ReactiveMongoApi]

  val sessionRepo = injector.instanceOf[SessionRepo]

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

  Await.result(
    sessionRepo.insert(mockSessionModel),
    Duration.Inf
  )

  application.stop()

}
