package lib.jsonapi

import play.api.libs.json.{JsObject, Json}

trait DataIdentifierResource extends Resource {

  val topLevelTag: String = "data"

  val `type`: String

  val id: String

  val toJsonApi: JsObject =
    Json.obj(
      s"$topLevelTag" ->
        Json.obj(
          "type" -> `type`,
          "id" -> id
        )
    )

}
