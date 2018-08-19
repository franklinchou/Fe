package services

import com.google.inject.{Inject, Singleton}
import play.api.libs.ws.WSClient


@Singleton
class GenericService @Inject()(ws: WSClient,
                               app: play.api.Application) {


}
