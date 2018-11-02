package marshall.jsonapi.resources

import resources.ExerciseResource
import models.ExerciseModel
import org.scalatest.FunSpec
import play.api.libs.json.Json

class ExerciseResourceSpec extends FunSpec {

  describe("Exercise resource") {
    it("should marshall exercise -> exercise-resource") {
      val model: ExerciseModel = marshall.mockBenchPress

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
