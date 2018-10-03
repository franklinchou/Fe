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


  println(
    Json.prettyPrint(resource.toJsonApi)
  )


}
