package models

import java.time.LocalDate
import java.util.UUID

import ai.x.play.json.Jsonx
import lib.containers.StringContainer
import models.strength.SetModel
import play.api.libs.json.OFormat


object SessionModel {

  /**
    * Json format
    */
  implicit lazy val jsFormat: OFormat[SessionModel] = Jsonx.formatCaseClass[SessionModel]


  /**
    * Create a [[SessionModel]]
    *
    * @param date
    * @param sets
    * @return
    */
  def apply(date: LocalDate,
            sets: List[SetModel]): SessionModel = {

    val uuid = UUID.randomUUID().toString
    val id = StringContainer[AbstractModelId](uuid)

    SessionModel(
      id = id,
      date = date,
      sets = sets
    )
  }


}

case class SessionModel(id: StringContainer[AbstractModelId],
                        date: LocalDate,
                        sets: List[SetModel]) extends AbstractModel