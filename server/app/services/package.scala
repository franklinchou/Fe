import lib.containers.StringContainer
import models.AbstractUserId

package object services {

  type UserId = StringContainer[AbstractUserId]

}
