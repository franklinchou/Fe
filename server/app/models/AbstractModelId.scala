package models

import ai.x.play.json.Jsonx
import play.api.libs.json.OFormat

object AbstractModelId {

  /**
    * Json formatting
    */
  implicit lazy val jsFormat: OFormat[AbstractModelId] = Jsonx.formatCaseClass[AbstractModelId]

}

final case class AbstractModelId(str: String) extends AnyVal
