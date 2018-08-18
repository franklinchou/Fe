package models

import containers.StringContainer


case class ExerciseModel(id: StringContainer[AbstractModelId],
                         exercise: StringContainer[ExerciseModel],
                         description: String,
                         variation: String
                         /* duration: Option[???] */) extends AbstractModel