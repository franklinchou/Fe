package lib.jsonapi

import play.api.libs.json.JsObject

trait BaseResource {

  val id: String

  val topLevelTag: String

  val toJsonApi: JsObject

}
