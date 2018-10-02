package jsonapi

import play.api.libs.json.JsObject

trait Resource {

  val topLevelTag: String

  val toJsonApi: JsObject

}
