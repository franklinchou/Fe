package lib.jsonapi

import play.api.libs.json.JsObject

trait BaseResource {

  lazy val topLevelTag: String = "data"

  val toJsonApi: JsObject

}
