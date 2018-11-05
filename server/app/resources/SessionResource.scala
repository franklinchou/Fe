package resources

import java.time.LocalDate

import lib.containers.StringContainer
import lib.jsonapi.{DataResourceIncluded, Resource2IncludedResource}
import models.{AbstractModelId, SessionModel}
import models.strength.SetModel
import play.api.libs.json._

object SessionResource {

  final val `type`: String = "session"

  implicit lazy val reads: Reads[SessionResource] = (js: JsValue) => {

    val idOpt = (js \ "id").validate[String].asOpt
    val dateOpt = (js \ "attributes" \ "date").validate[LocalDate].asOpt
    val `type` = (js \ "type").validate[String].asOpt.getOrElse("")
    val isCorrectType = `type` == this.`type`

    val sets =
      (js \ "included")
        .validate[List[SetResource]]
        .fold(
          _ => List.empty[SetModel],
          success => success.map(_.model)
        )

    if (isCorrectType) {
      JsError(s"Expected ${this.`type`}, got ${`type`}.")
    } else {
      (idOpt, dateOpt, sets) match {
        case (Some(id), Some(date), s: Seq[SetModel]) =>
          val idContainer = StringContainer[AbstractModelId](id)
          val model = SessionModel(idContainer, date, s)
          val resource = SessionResource(model)
          JsSuccess(resource)
        case (None, Some(date), s: Seq[SetModel]) =>
          val model = SessionModel(date, s)
          val resource = SessionResource(model)
          JsSuccess(resource)
        case (_, _, _) => JsError()
      }
    }
  }

}


/**
  * Create a [[SessionResource]] from a [[SessionModel]]
  *
  * @param sessionModel
  */
case class SessionResource(sessionModel: SessionModel) extends DataResourceIncluded {

  lazy val `type`: String = SessionResource.`type`

  lazy val id: String = sessionModel.id.value

  lazy val attributes: Option[JsObject] =
    Some(
      Json.obj(
        "date" -> sessionModel.date.toString
      )
    )

  private val setType = SetResource.`type`

  lazy val relationships: Option[JsObject] =
    Some(
      sessionModel
        .sets
        .map(_.id.value)
        .map(id => Json.obj(setType -> id))
        .fold(Json.obj())(_ ++ _)
    )

  lazy val links: Option[JsObject] = None

  lazy val included: List[JsObject] = List.empty[JsObject]

  override lazy val toJsonApi: JsObject =
    Json.obj(
      "type" -> `type`,
      "id" -> sessionModel.id,
      "attributes" -> attributes,
      "relationships" -> relationships
    )

}
