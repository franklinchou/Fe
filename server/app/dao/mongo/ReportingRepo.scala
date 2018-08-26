package dao.mongo

import com.google.inject.Inject
import lib.containers.StringContainer
import models.{AbstractModel, AbstractModelId, SessionModel}
import play.modules.reactivemongo.ReactiveMongoApi

import scala.concurrent.{ExecutionContext, Future}



class ReportingRepo @Inject()(val rma: ReactiveMongoApi)
                             (implicit ec: ExecutionContext) extends MongoRepo[SessionModel] {

  /**
    * Name of the collection where records are stored
    */
  val collectionName: String = "sessions"

  /**
    * Insert a record into the database.
    *
    * @param record
    * @return
    */
  def create(record: AbstractModel): Future[Boolean] = ???

  /**
    * Upsert a record into the database.
    *
    * @param record
    * @return
    */
  def upsert(record: AbstractModel): Future[Boolean] = ???

  /**
    * Remove a record from the database.
    *
    * @param id
    * @return
    */
  def delete(id: StringContainer[AbstractModelId]): Future[Boolean] = ???
}
