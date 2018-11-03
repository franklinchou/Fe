package resources

import lib.jsonapi.CompoundDataResource
import models.SessionModel
import play.api.libs.json.{JsArray, JsObject, Json}

case class SessionResources(models: Seq[SessionModel]) extends CompoundDataResource {

  val `type`: String = "sessions"

  val data: Seq[SessionResource] =
    models.map(m => SessionResource(m))

  val relationships: Option[JsObject] = None

  val links: Option[JsObject] = None

  /**
    * In a compound document, all included resources MUST be represented as an
    * array of resource objects in a top-level included member.
    */
  val included: List[SetResource] =
    models
      .par
      .map(m => m.sets)
      .flatMap(ss => ss.map(s => SetResource.apply(s)))
      .toList

  val toJsonApi: JsObject = {
    val dataAsArray = JsArray.apply(data.map(_.toJsonApi))
    val includedAsArray = JsArray.apply(included.map(_.toJsonApi))
    Json.obj(
      "data" -> dataAsArray,
      "included" -> includedAsArray
    )
  }

}
