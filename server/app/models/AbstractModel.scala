package models

import lib.containers.StringContainer
import play.api.libs.json._

trait AbstractModel {

  /**
    * Id of the model
    */
  val id: StringContainer[AbstractModelId]

}


object AbstractModel {

  implicit object AbstractModelMarshaller extends Writes[AbstractModel] {
    def writes(am: AbstractModel): JsObject = {
      Json.obj("id" -> am.id.toString)
    }
  }

}
