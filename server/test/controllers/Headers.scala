package controllers

import play.api.test.FakeHeaders

trait Headers {

  val NO_USER = FakeHeaders(Seq("user" -> ""))
  val TEST_USER = FakeHeaders(Seq("user" -> "fake-user-1"))

}


object Headers extends Headers