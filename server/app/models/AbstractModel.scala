package models

import lib.containers.StringContainer
import play.api.libs.json.{Json, Writes}

object AbstractModel {

  implicit lazy val modelWriter: Writes[AbstractModel] = (m: AbstractModel) =>
    Json.obj(
      "id" -> m.id
    )

}


trait AbstractModel {


  /**
    * Id of the model
    */
  val id: StringContainer[AbstractModelId]

}
