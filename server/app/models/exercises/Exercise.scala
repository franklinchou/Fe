package models.exercises

import play.api.libs.json._

object Exercise {

  /**
    * Json format
    */
  implicit val jsWrites = new Writes[Exercise] {
    override def writes(obj: Exercise): JsValue = {
      Json.obj("exercise" -> obj.getClass.getSimpleName)
    }
  }
}



trait Exercise