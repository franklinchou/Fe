package models

import containers.StringContainer
import play.api.libs.json.{JsObject, Json}

trait AbstractJsonRecord extends Product with Serializable {

  /**
    * Id of the record
    */
  val id: StringContainer[AbstractModelId]


  /**
    * Convert the record to Json
    */
  implicit def toJson: JsObject = Json.toJsObject(this)

}