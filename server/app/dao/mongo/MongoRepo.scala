package dao.mongo

import dao.AbstractDao
import models.{AbstractJsonModel, AbstractModelId}
import play.api.Logger
import play.api.libs.json.Json
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.play.json.collection.JSONCollection

import scala.concurrent.{ExecutionContext, Future}

trait MongoRepo[R <: AbstractJsonModel] extends AbstractDao {

  val reactiveMongoApi: ReactiveMongoApi


  /**
    * Database
    */
  val db = reactiveMongoApi.database


  /**
    * Name of the collection where records are stored
    */
  val collectionName: String


  def collection()(implicit ec: ExecutionContext): Future[JSONCollection] = {
    db.map(_.collection[JSONCollection](collectionName))
  }


  /**
    * Insert a record into the collection
    *
    * @param record
    * @return
    */
  def create(record: R): Future[Boolean] = {
    Logger.info(s"Inserting record ${record.id.str} into $collectionName")

    collection
      .flatMap(_.insert(record.toJson))
      .map(_.ok)
  }


  /**
    * Upsert a record into the collection
    *
    * @param record
    * @param ec
    * @return
    */
  def upsert(record: R)(implicit ec: ExecutionContext): Future[Boolean] = {
    Logger.info(s"Upserting record ${record.id.str} into $collectionName")
    val selector = Json.obj("id" -> record.id.str)
    val modifier = Json.obj("$set" -> record.toJson)

    collection
      .flatMap(_.update(selector, modifier, upsert = true))
      .map(_.ok)
  }


  /**
    * Remove a record from the collection
    *
    * @param id
    * @param ec
    * @return
    */
  def delete(id: AbstractModelId)(implicit ec: ExecutionContext): Future[Boolean] = {
    Logger.info(s"Deleting record ${id.str} from $collectionName")
    val selector = Json.obj("id" -> id.str)

    collection
      .flatMap(_.remove(selector))
      .map(_.ok)
  }

}
