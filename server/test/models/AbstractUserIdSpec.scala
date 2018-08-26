package models

import org.scalatest.FunSpec
import play.api.libs.json.Json

class AbstractUserIdSpec extends FunSpec {

  describe("A user id") {

    val test = "test-user"

    val user: AbstractUserId = AbstractUserId(test)

    val expected =
      """
        |{"user":"test-user"}
      """.stripMargin

    it("should marshall to json") {
      assert(Json.toJson(user) == Json.parse(expected))
    }
  }

}
