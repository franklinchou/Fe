package lib.jsonapi

import play.api.libs.json.{JsObject, Json}

trait DataResource extends DataIdentifierResource {

  val `type`: String

  val id: String

  val attributes: Option[JsObject]

  val relationships: Option[JsObject]

  val links: Option[JsObject]

  private lazy val includable = {
    Map(
      "attributes" -> attributes,
      "relationships" -> relationships,
      "links" -> links,
      "meta" -> meta
    ).filter(_._2.isDefined)
  }

  override val toJsonApi: JsObject = {
    val base =
      Json.obj(
        "type" -> `type`,
        "id" -> id
      )
    val inner = {
      includable.foldLeft(base) { (acc, pair) =>
        acc + (pair._1 -> pair._2.get)
      }
    }
    Json.obj(s"$topLevelTag" -> inner)
  }

}
