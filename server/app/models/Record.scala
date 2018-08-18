package models

import play.api.libs.json.{JsObject, Json}


trait Record {

  /**
    * This model's id
    */
  val id: AbstractModelId


  /**
    * Coerce a generic record into a Json object
    *
    * @return
    */
  def toJson: JsObject = Json.obj("id" -> id.str)

}