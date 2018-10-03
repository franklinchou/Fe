package lib.jsonapi

import play.api.libs.json.{JsObject, Json}

/**
  * Corresponds to Compound Documents
  * http://jsonapi.org/format/#document-compound-documents
  */
trait CompoundDataResource extends DataResource {

  /**
    * In a compound document, all included resources MUST be represented as an
    * array of resource objects in a top-level included member.
    */
  val included: List[JsObject]

  lazy val meta: Option[JsObject] =
    Some(
      Json.obj("count" -> included.size)
    )


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

    Json.obj(
      "data" -> inner,
      "included" -> included
    )
  }

}
