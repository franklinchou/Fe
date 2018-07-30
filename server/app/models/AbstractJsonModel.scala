package models

import play.api.libs.json.JsObject

trait AbstractJsonModel extends AbstractModel {


  def toJson: JsObject


}
