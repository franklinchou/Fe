package marshall.jsonapi.resources

import lib.containers.StringContainer
import lib.jsonapi.resources.SetResource
import marshall.mockBenchPress
import models.AbstractModelId
import models.strength.SetModel
import org.scalatest.FunSpec
import play.api.libs.json.Json

class SetResourceSpec extends FunSpec {

  val resource = SetResource.apply(
    SetModel(
      StringContainer.apply[AbstractModelId](s"mock-bench-set-1"),
      5,
      mockBenchPress
    )
  )

  val expected: String =
    """
      |{
      |  "data" : {
      |    "type" : "set",
      |    "id" : "mock-bench-set-1",
      |    "attributes" : {
      |      "multiplier" : 5,
      |      "exercise" : "bench-press",
      |      "description" : "",
      |      "variation" : "Standard bench press",
      |      "weight" : 135
      |    },
      |    "relationships" : {
      |      "exercise" : {
      |        "data" : {
      |          "type" : "bench-press",
      |          "id" : "mock-exercise-1"
      |        }
      |      }
      |    }
      |  }
      |}
    """.stripMargin

  describe("Set model") {
    it("should marshall to JsonApi") {

      assert(resource.toJsonApi == Json.parse(expected))
    }
  }

}