package lib.jsonapi.resources

import lib.containers.StringContainer
import lib.jsonapi.DataResource
import models.strength.SetModel
import models.{AbstractModelId, ExerciseModel}
import play.api.libs.json._


object SetResource {

  final val `type`: String = "set"

  implicit lazy val reads: Reads[SetResource] = (js: JsValue) => {

    val idOpt = (js \ "id").validate[String].asOpt
    val isCorrectType = `type` == this.`type`

    // This should default to 1, but that logic should be handled on the front end
    val multiplierOpt = (js \ "attributes" \ "multiplier").validate[Int].asOpt

    val exercises: Option[ExerciseModel] =
      (js \ "relationships")
        .validate[ExerciseResource]
        .fold(
          _ => None,
          success => Some(success.exerciseModel)
        )

    if (isCorrectType) {
      JsError(s"Expected ${this.`type`}, got ${`type`}.")
    } else {
      (idOpt, multiplierOpt, exercises) match {
        case (Some(id), Some(multiplier), Some(em)) =>
          val idContainer = StringContainer[AbstractModelId](id)
          val model = SetModel(idContainer, multiplier, em)
          val resource = SetResource(model)
          JsSuccess(resource)
        case (None, Some(multiplier), Some(em)) =>
          val model = SetModel(multiplier, em)
          val resource = SetResource(model)
          JsSuccess(resource)
        case (_, _, _) => JsError()
      }
    }

  }

}

case class SetResource(model: SetModel) extends DataResource {

  lazy val `type`: String = SetResource.`type`

  lazy val id: String = model.id.value

  lazy val attributes: Option[JsObject] = {

    val exerciseModel = model.exercise

    val json =
      Json.obj(
        "multiplier" -> model.multiplier,
        "exercise" -> exerciseModel.exercise,
        "description" -> exerciseModel.description,
        "variation" -> exerciseModel.variation,
        "weight" -> exerciseModel.weight
      )
    Some(json)
  }

  lazy val relationships: Option[JsObject] = {
    val json = ExerciseRelationshipResource.apply(model.exercise).toJsonApi
    Some(json)
  }

  lazy val links: Option[JsObject] = None

  lazy val meta: Option[JsObject] = None

}
