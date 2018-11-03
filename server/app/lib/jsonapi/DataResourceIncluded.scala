package lib.jsonapi

import play.api.libs.json.{JsObject, Json}


trait DataResourceIncluded extends DataResource {

  /**
    * In a compound document, all included resources MUST be represented as an
    * array of resource objects in a top-level included member.
    */
  val included: List[JsObject]

  lazy val meta: Option[JsObject] = Some(Json.obj("count" -> included.size))

  override lazy val toJsonApi: JsObject = {
    Json.obj(
      "data" -> reduce(affiliates, base),
      "included" -> included
    )
  }

}
