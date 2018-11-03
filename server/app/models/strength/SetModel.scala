package models.strength

import java.util.UUID

import ai.x.play.json.Jsonx
import lib.containers.StringContainer
import models.{AbstractModelId, BaseModel, ExerciseModel}
import play.api.libs.json.{JsObject, OFormat}


object SetModel {

  /**
    * Json format
    */
  implicit lazy val jsFormat: OFormat[SetModel] = Jsonx.formatCaseClass[SetModel]


  /**
    * Create a [[SetModel]]
    *
    * @param multiplier
    * @param exercise
    * @return
    */
  def apply(multiplier: Int,
            exercise: ExerciseModel): SetModel = {

    val uuid = UUID.randomUUID().toString
    val id = StringContainer[AbstractModelId](uuid)

    SetModel(
      id = id,
      multiplier = multiplier,
      exercise = exercise
    )
  }

}


case class SetModel(id: StringContainer[AbstractModelId],
                    multiplier: Int,
                    exercise: ExerciseModel) extends BaseModel