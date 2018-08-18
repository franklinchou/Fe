package models.strength

import ai.x.play.json.Jsonx
import lib.containers.StringContainer
import models.{AbstractModel, AbstractModelId, ExerciseModel, SessionModel}
import play.api.libs.json.OFormat


object SetModel {

  /**
    * Json format
    */
  implicit lazy val jsFormat: OFormat[SetModel] = Jsonx.formatCaseClass[SetModel]


}


case class SetModel(id: StringContainer[AbstractModelId],
                    multiplier: Int,
                    exercise: ExerciseModel) extends AbstractModel