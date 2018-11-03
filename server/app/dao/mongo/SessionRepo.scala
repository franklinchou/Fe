package dao.mongo

import com.google.inject.Inject
import lib.containers.StringContainer
import lib.jsonapi.BaseResource
import models._
import play.api.libs.json.Json
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.api.{Cursor, ReadPreference}
import reactivemongo.play.json._

import scala.concurrent.{ExecutionContext, Future}

class SessionRepo @Inject()(val rma: ReactiveMongoApi)
                           (implicit ec: ExecutionContext) extends MongoRepo[SessionModel] {

  /**
    * Name of the collection where records are stored
    */
  val collectionName: String = "exercise"

  /**
    * Find a list of [[SessionModel]]s given a user id
    *
    * @param user
    * @return
    */
  def find(user: StringContainer[AbstractUserId]): Future[List[SessionModel]] = {

    // TODO Make find by user actually work
    // val query: JsObject = Json.obj("user" -> user.value)
    val query = Json.obj()


    collection
      .map(_.find(query).cursor[SessionModel](ReadPreference.primary))
      .flatMap(_.collect[List](-1, Cursor.FailOnError[List[SessionModel]]()))
  }

  /**
    * Upsert a record into the database.
    *
    * @param record
    * @return
    */
  def upsert(record: BaseResource): Future[Boolean] = ???

  /**
    * Remove a record from the database.
    *
    * @param id
    * @return
    */
  def delete(id: StringContainer[AbstractModelId]): Future[Boolean] = ???

}
