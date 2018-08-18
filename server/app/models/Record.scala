package models

import play.api.libs.json.JsObject


trait Record {

  /**
    * This model's id
    */
  val id: AbstractModelId


  /**
    * Create json from object
    *
    * @return
    */
  def toJson: JsObject

}