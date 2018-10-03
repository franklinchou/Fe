package models.exercises

import play.api.libs.json._

object Exercise {

  /**
    * Json format
    */
  implicit object ExerciseMarshaller extends Writes[Exercise] {
    def writes(ge: Exercise): JsValue = ge match {
      case BenchPress => Json.toJson("bench-press")
      case DeadLift => Json.toJson("dead-lift")
      case Squat => Json.toJson("squat")
    }
  }

  implicit object ExerciseUnmarshaller extends Reads[Exercise] {
    override def reads(json: JsValue): JsResult[Exercise] = {
      json.validate[String].getOrElse("") match {
        case "bench-press" => JsSuccess(BenchPress)
        case "dead-lift" => JsSuccess(DeadLift)
        case "squat" => JsSuccess(Squat)
        case "" => JsError("Invalid exercise found")
      }
    }
  }
}



trait Exercise