package marshall.models

import marshall._
import org.scalatest.FunSpec
import play.api.libs.json.Json


class SessionModelSpec extends FunSpec {

  describe("A work out session") {

    it("should marshall to json") {
      val actual = Json.toJson(mockSession)

      val expected =
        s"""
           |{
           |  "id" : "mock-session",
           |  "date" : "$date",
           |  "sets" : [ {
           |    "id" : "mock-bench-set 0",
           |    "multiplier" : 5,
           |    "exercise" : {
           |      "id" : "mock-exercise-1",
           |      "exercise" : "bench-press",
           |      "description" : "",
           |      "variation" : "Standard bench press",
           |      "weight" : 135
           |    }
           |  }, {
           |    "id" : "mock-bench-set 1",
           |    "multiplier" : 5,
           |    "exercise" : {
           |      "id" : "mock-exercise-1",
           |      "exercise" : "bench-press",
           |      "description" : "",
           |      "variation" : "Standard bench press",
           |      "weight" : 135
           |    }
           |  }, {
           |    "id" : "mock-bench-set 2",
           |    "multiplier" : 5,
           |    "exercise" : {
           |      "id" : "mock-exercise-1",
           |      "exercise" : "bench-press",
           |      "description" : "",
           |      "variation" : "Standard bench press",
           |      "weight" : 135
           |    }
           |  }, {
           |    "id" : "mock-bench-set 3",
           |    "multiplier" : 5,
           |    "exercise" : {
           |      "id" : "mock-exercise-1",
           |      "exercise" : "bench-press",
           |      "description" : "",
           |      "variation" : "Standard bench press",
           |      "weight" : 135
           |    }
           |  }, {
           |    "id" : "mock-bench-set 4",
           |    "multiplier" : 5,
           |    "exercise" : {
           |      "id" : "mock-exercise-1",
           |      "exercise" : "bench-press",
           |      "description" : "",
           |      "variation" : "Standard bench press",
           |      "weight" : 135
           |    }
           |  }, {
           |    "id" : "mock-bench-set 5",
           |    "multiplier" : 5,
           |    "exercise" : {
           |      "id" : "mock-exercise-1",
           |      "exercise" : "bench-press",
           |      "description" : "",
           |      "variation" : "Standard bench press",
           |      "weight" : 135
           |    }
           |  } ]
           |}
        """.stripMargin

      assert(Json.parse(expected) == actual)
    }

  }

}
