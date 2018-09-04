package models

import lib.containers.StringContainer
import play.api.libs.json.{JsObject, Json}

trait AbstractModel {

  /**
    * Id of the model
    */
  val id: StringContainer[AbstractModelId]


  /**
    * Coerce a generic record into a Json object
    *
    * @return
    */
  def toJson: JsObject = Json.obj("id" -> id.toString)  // TODO This doesn't work.


}
