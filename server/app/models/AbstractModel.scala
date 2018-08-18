package models

import containers.StringContainer

trait AbstractModel {

  val id: StringContainer[AbstractModelId]

}
