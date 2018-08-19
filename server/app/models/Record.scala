package models

import lib.containers.StringContainer
import play.api.libs.json.{JsObject, Json}

trait Record {

  /**
    * Id of the record
    */
  val id: StringContainer[AbstractModelId]


  /**
    * Coerce a generic record into a Json object
    *
    * @return
    */
  def toJson: JsObject = Json.obj("id" -> id.toString)

}