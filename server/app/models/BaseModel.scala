package models

import lib.containers.StringContainer
import play.api.libs.json.{Json, OWrites}

object BaseModel {

  implicit lazy val write: OWrites[BaseModel] = (m: BaseModel) => {
    Json.obj("id" -> m.id.value)
  }

}

trait BaseModel {

  val id: StringContainer[AbstractModelId]

}
