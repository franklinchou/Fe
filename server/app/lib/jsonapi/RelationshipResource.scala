package lib.jsonapi

import play.api.libs.json.{JsObject, Json}

trait RelationshipResource extends Resource {

  val topLevelTag: String

  val data: JsObject

  val toJsonApi: JsObject =
    Json.obj(
      topLevelTag -> Json.obj(
        "data" -> data
      )
    )

}
