package lib.jsonapi

import play.api.libs.json.{JsObject, Json}

/**
  * Corresponds to "ResourceIdentifierObject"
  * See http://jsonapi.org/format/#document-resource-identifier-objects
  */
trait DataIdResource extends BaseResource {

  val `type`: String

  val id: String

  val meta: Option[JsObject]

  lazy val toJsonApi: JsObject =
    Json.obj(
      topLevelTag ->
        Json.obj(
          "type" -> `type`,
          "id" -> id
        )
    )
}
