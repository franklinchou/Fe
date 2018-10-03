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

  lazy val meta: Option[JsObject] = Some(Json.obj("count" -> included.size))

  override val toJsonApi: JsObject = {
    Json.obj(
      "data" -> reduce(affiliates, base),
      "included" -> included
    )
  }

}
