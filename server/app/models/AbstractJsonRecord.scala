package models

import play.api.libs.json.JsObject

trait AbstractJsonRecord extends Product with Serializable {

  /**
    * Id of the record
    */
  val id: AbstractModelId


  /**
    * Convert the record to Json
    */
  def toJson: JsObject

}