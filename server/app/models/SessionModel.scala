package models

import java.time.LocalDate

import ai.x.play.json.Jsonx
import lib.containers.StringContainer
import models.strength.SetModel
import play.api.libs.json.OFormat


object SessionModel {

  /**
    * Json format
    */
  implicit lazy val jsFormat: OFormat[SessionModel] = Jsonx.formatCaseClass[SessionModel]

}

case class SessionModel(id: StringContainer[AbstractModelId],
                        date: LocalDate,
                        sets: List[SetModel]) extends AbstractModel