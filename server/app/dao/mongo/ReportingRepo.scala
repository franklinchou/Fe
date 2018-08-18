package dao.mongo

import com.google.inject.Inject
import models.SessionModel
import play.modules.reactivemongo.ReactiveMongoApi

import scala.concurrent.ExecutionContext



class ReportingRepo @Inject()(val rma: ReactiveMongoApi)
                             (implicit ec: ExecutionContext) extends MongoRepo[SessionModel] {

  /**
    * Name of the collection where records are stored
    */
  val collectionName: String = "sessions"


}
