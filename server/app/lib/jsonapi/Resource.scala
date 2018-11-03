package lib.jsonapi

import play.api.libs.json.JsObject

trait Resource {

  val id: String

  val topLevelTag: String

  val toJsonApi: JsObject

}
