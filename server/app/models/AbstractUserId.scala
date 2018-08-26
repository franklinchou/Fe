package models

import ai.x.play.json.Jsonx
import play.api.libs.json.OFormat

object AbstractUserId {

  /**
    * Json formatting
    */
  implicit lazy val jsFormat: OFormat[AbstractUserId] = Jsonx.formatCaseClass[AbstractUserId]

}


final case class AbstractUserId(user: String) extends AnyVal
