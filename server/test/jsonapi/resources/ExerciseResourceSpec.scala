package jsonapi.resources

import lib.containers.StringContainer
import models.exercises.BenchPress
import models.{AbstractModelId, ExerciseModel}
import org.scalatest.FunSpec
import play.api.libs.json.Json

class ExerciseResourceSpec extends FunSpec {

  describe("Exercise resource") {
    it("should marshall exercise -> exercise-resource") {
      val model: ExerciseModel =
        ExerciseModel(
          StringContainer.apply[AbstractModelId]("mock-exercise-1"),
          BenchPress,
          "",
          "Standard bench press",
          135.00
        )

      val resource = ExerciseResource.apply(model)

      val validJsonApi =
        """
          |{
          |  "data" : {
          |    "type" : "exercise",
          |    "id" : "mock-exercise-1",
          |    "attributes" : {
          |      "exercise" : "BenchPress",
          |      "description" : "",
          |      "variation" : "Standard bench press",
          |      "weight" : 135
          |    }
          |  }
          |}
        """
          .stripMargin

      assert(Json.parse(validJsonApi) === resource.toJsonApi)
    }
  }

}
