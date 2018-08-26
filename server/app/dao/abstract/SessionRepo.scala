package dao.`abstract`

import com.google.inject.ImplementedBy
import dao.mongo.SessionMongoRepo
import lib.containers.StringContainer
import models.{AbstractModelId, AbstractUserId, AbstractModel, SessionModel}

import scala.concurrent.{ExecutionContext, Future}


@ImplementedBy(classOf[SessionMongoRepo])
abstract class SessionRepo()(implicit ec: ExecutionContext) {

  /**
    * Find a list of [[SessionModel]]s given a user id
    *
    * @param user
    * @return
    */
  def find(user: StringContainer[AbstractUserId]): Future[List[SessionModel]]

  /**
    * Upsert a record into the database.
    *
    * @param record
    * @return
    */
  def upsert(record: AbstractModel): Future[Boolean]

  /**
    * Remove a record from the database.
    *
    * @param id
    * @return
    */
  def delete(id: StringContainer[AbstractModelId]): Future[Boolean]

}
