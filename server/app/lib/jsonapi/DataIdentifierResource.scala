package lib.jsonapi

import play.api.libs.json.{JsObject, Json}

/**
  * Corresponds to "ResourceIdentifierObject"
  * See http://jsonapi.org/format/#document-resource-identifier-objects
  */
trait DataIdentifierResource extends Resource {

  val topLevelTag: String = "data"

  val `type`: String

  val id: String

  val meta: Option[JsObject]

  val toJsonApi: JsObject =
    Json.obj(
      s"$topLevelTag" ->
        Json.obj(
          "type" -> `type`,
          "id" -> id
        )
    )
}
