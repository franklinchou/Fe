package lib.jsonapi.resources

import lib.jsonapi.DataResource
import play.api.libs.json.{JsObject, Json}

case class Resource2IncludedResource(dr: DataResource) extends DataResource {

  lazy val `type`: String = dr.`type`

  lazy val id: String = dr.id

  lazy val attributes: Option[JsObject] = dr.attributes

  lazy val relationships: Option[JsObject] = dr.relationships

  lazy val links: Option[JsObject] = dr.links

  lazy val meta: Option[JsObject] = dr.meta

  // TODO DRY
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
    inner
  }


}
