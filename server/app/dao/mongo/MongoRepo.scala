package dao.mongo

import containers.StringContainer
import dao.UnstructuredDao
import models.{AbstractJsonRecord, AbstractModelId}
import play.api.Logger
import play.api.libs.json.Json
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.play.json.collection.JSONCollection

import scala.concurrent.{ExecutionContext, Future}

trait MongoRepo[R <: AbstractJsonRecord] extends UnstructuredDao {

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
  def create(record: R)(implicit ec: ExecutionContext): Future[Boolean] = {
    Logger.info(s"Inserting record ${record.id.toString} into $collectionName")

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
    val id = record.id.toString
    val selector = Json.obj("id" -> id)
    val modifier = Json.obj("$set" -> record.toJson)

    Logger.info(s"Upserting record $id into $collectionName")

    collection
      .flatMap(_.update(selector, modifier, upsert = true))
      .map(_.ok)
  }


  /**
    * Remove a record from the collection
    *
    * @param modelId
    * @param ec
    * @return
    */
  def delete(modelId: StringContainer[AbstractModelId])(implicit ec: ExecutionContext): Future[Boolean] = {
    Logger.info(s"Deleting record ${modelId.id} from $collectionName")
    val selector = Json.obj("id" -> modelId.id)

    collection
      .flatMap(_.remove(selector))
      .map(_.ok)
  }

}
