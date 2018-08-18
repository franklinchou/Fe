package models.strength

import models.{AbstractModelId, Id}

case class SetModel(id: Id[AbstractModelId],
                    exercise: String,
                    time: Option[String])