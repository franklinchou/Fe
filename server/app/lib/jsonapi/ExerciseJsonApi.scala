package lib.jsonapi

import models.exercises.{BenchPress, DeadLift, Exercise, Squat}

object ExerciseJsonApi {

  /**
    * Convert [[Exercise]] model to string for marshalling to JsonApi
    *
    * @param e
    * @return
    */
  def apply(e: Exercise): String = {
    e match {
      case BenchPress => "bench-press"
      case DeadLift => "dead-lift"
      case Squat => "squat"
    }
  }
}

case class ExerciseJsonApi(e: Exercise)