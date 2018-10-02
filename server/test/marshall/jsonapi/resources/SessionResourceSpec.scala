package marshall.jsonapi.resources

import lib.jsonapi.resources.SessionResource
import marshall.mockSession
import org.scalatest.FunSpec
import play.api.libs.json.Json

class SessionResourceSpec extends FunSpec {

  val resource = SessionResource.apply(mockSession)

  println(
    Json.prettyPrint(resource.toJsonApi)
  )

}
