package models.exercises

import play.api.libs.json._

object Exercise {

  /**
    * Json format
    */
  implicit object ExerciseMarshaller extends Writes[Exercise] {
    def writes(ge: Exercise): JsValue = ge match {
      case BenchPress => Json.toJson("BenchPress")
      case DeadLift => Json.toJson("DeadLift")
      case Squat => Json.toJson("Squat")
    }
  }

  implicit object ExerciseUnmarshaller extends Reads[Exercise] {
    def reads(json: JsValue): JsResult[Exercise] = {
      json.validate[String].getOrElse("") match {
        case "BenchPress" => JsSuccess(BenchPress)
        case "DeadLift" => JsSuccess(DeadLift)
        case "Squat" => JsSuccess(Squat)
        case "" => JsError("Invalid exercise found")
      }
    }
  }
}



trait Exercise