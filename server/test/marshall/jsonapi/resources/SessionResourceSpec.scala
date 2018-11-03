package marshall.jsonapi.resources

import java.time.LocalDate

import resources.SessionResource
import marshall.mockSession
import org.scalatest.FunSpec
import play.api.libs.json.Json

class SessionResourceSpec extends FunSpec {

  val resource = SessionResource.apply(mockSession)
  
  val expected: String =
    s"""
       |{
       |  "data" : {
       |    "type" : "exercise-session",
       |    "id" : "mock-session",
       |    "attributes" : {
       |      "date" : "${LocalDate.now}"
       |    },
       |    "meta" : {
       |      "count" : 6
       |    }
       |  },
       |  "included" : [ {
       |    "type" : "set",
       |    "id" : "mock-bench-set 0",
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
       |          "id" : "mock-exercise-1",
       |          "type" : "bench-press"
       |        }
       |      }
       |    }
       |  }, {
       |    "type" : "set",
       |    "id" : "mock-bench-set 1",
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
       |          "id" : "mock-exercise-1",
       |          "type" : "bench-press"
       |        }
       |      }
       |    }
       |  }, {
       |    "type" : "set",
       |    "id" : "mock-bench-set 2",
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
       |          "id" : "mock-exercise-1",
       |          "type" : "bench-press"
       |        }
       |      }
       |    }
       |  }, {
       |    "type" : "set",
       |    "id" : "mock-bench-set 3",
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
       |          "id" : "mock-exercise-1",
       |          "type" : "bench-press"
       |        }
       |      }
       |    }
       |  }, {
       |    "type" : "set",
       |    "id" : "mock-bench-set 4",
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
       |          "id" : "mock-exercise-1",
       |          "type" : "bench-press"
       |        }
       |      }
       |    }
       |  }, {
       |    "type" : "set",
       |    "id" : "mock-bench-set 5",
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
       |          "id" : "mock-exercise-1",
       |          "type" : "bench-press"
       |        }
       |      }
       |    }
       |  } ]
       |}
    """.stripMargin

  describe("Session model") {
    it("should marshall to JsonApi") {
      assert(resource.toJsonApi == Json.parse(expected))
    }
  }

}
