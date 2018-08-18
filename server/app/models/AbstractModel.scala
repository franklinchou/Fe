package models

import lib.containers.StringContainer

trait AbstractModel {

  val id: StringContainer[AbstractModelId]

}
