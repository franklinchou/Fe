package models

import java.util.UUID

import ai.x.play.json.Jsonx
import lib.containers.StringContainer
import models.exercises._
import play.api.libs.json._

object ExerciseModel {

  /**
    * Json format
    */
  implicit lazy val jsFormat: OFormat[ExerciseModel] = Jsonx.formatCaseClass[ExerciseModel]


  /**
    * Create an [[ExerciseModel]]
    *
    * Note that in a /POST method, the call should not include an id. The backend will
    * generate an id and return the id (via jsonapi) in the response.
    *
    * @param exercise
    * @param description
    * @param variation
    * @param weight
    * @return
    */
  def apply(exercise: Exercise,
            description: String,
            variation: String,
            weight: BigDecimal): ExerciseModel = {

    val uuid = UUID.randomUUID().toString
    val id = StringContainer[AbstractModelId](uuid)

    ExerciseModel(
      id = id,
      exercise = exercise,
      description = description,
      variation = variation,
      weight = weight
    )

  }



}

case class ExerciseModel(id: StringContainer[AbstractModelId],
                         exercise: Exercise,
                         description: String,
                         variation: String,
                         weight: BigDecimal
                         /* duration: Option[???] */) extends BaseModel