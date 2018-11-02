package models

import lib.containers.StringContainer

trait AbstractModel {

  /**
    * Id of the model
    */
  val id: StringContainer[AbstractModelId]

}
