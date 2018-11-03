package lib.jsonapi

import play.api.libs.json.{JsObject, Json}

case class Resource2IncludedResource(dr: DataResource) extends DataResource {

  lazy val `type`: String = dr.`type`

  lazy val id: String = dr.id

  lazy val attributes: Option[JsObject] = dr.attributes

  lazy val relationships: Option[JsObject] = dr.relationships

  lazy val links: Option[JsObject] = dr.links

  lazy val meta: Option[JsObject] = dr.meta

  override lazy val toJsonApi: JsObject = reduce(affiliates, base)

}
