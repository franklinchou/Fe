package resources

import lib.containers.StringContainer
import lib.jsonapi.DataResource
import models.{AbstractModelId, ExerciseModel}
import models.exercises.Exercise
import play.api.libs.json._

object ExerciseResource {

  final val `type`: String = "exercise"

  implicit lazy val reads: Reads[ExerciseResource] = (js: JsValue) => {

    val idOpt = (js \ "id").validate[String].asOpt
    val `type` = (js \ "type").validate[String].asOpt.getOrElse("")
    val attributes = (js \ "attributes").validate[JsObject].asOpt

    attributes
      .flatMap { attrs =>
        val exercise = (attrs \ "exercise").validate[Exercise].asOpt
        val description = (attrs \ "description").validate[String].asOpt
        val variation = (attrs \ "variation").validate[String].asOpt
        val weight = (attrs \ "weight").validate[BigDecimal].asOpt

        for {
          e <- exercise
          d <- description
          v <- variation
          w <- weight
          if `type` == this.`type`
        } yield {
          idOpt
            .map { id =>
              val idContainer = StringContainer[AbstractModelId](id)
              val model = ExerciseModel(idContainer, e, d, v, w)
              val resource = ExerciseResource(model)
              JsSuccess(resource)
            }
            .getOrElse {
              val model = ExerciseModel(e, d, v, w)
              val resource = ExerciseResource(model)
              JsSuccess(resource)
            }
        }
      }
      .getOrElse(JsError())
  }

}

case class ExerciseResource(exerciseModel: ExerciseModel) extends DataResource {

  lazy val `type`: String = ExerciseResource.`type`

  lazy val id: String = exerciseModel.id.value

  lazy val attributes: Option[JsObject] = {
    val json =
      Json.obj(
        "exercise" -> exerciseModel.exercise.toString,
        "description" -> exerciseModel.description,
        "variation" -> exerciseModel.variation,
        "weight" -> exerciseModel.weight
      )

    Some(json)
  }

  lazy val relationships: Option[JsObject] = None

  lazy val links: Option[JsObject] = None

  lazy val meta: Option[JsObject] = None

}
