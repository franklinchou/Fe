package dao.mongo

import dao.UnstructuredDAO
import lib.containers.StringContainer
import models.{AbstractModelId, BaseModel}
import play.api.Logger
import play.api.libs.json.{Json, OWrites}
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.api.DefaultDB
import reactivemongo.play.json._
import reactivemongo.play.json.collection.JSONCollection

import scala.concurrent.{ExecutionContext, Future}

trait MongoRepo[M <: BaseModel] extends UnstructuredDAO[M] {


  val rma: ReactiveMongoApi


  /**
    * Database
    */
  val db: Future[DefaultDB] = rma.database


  /**
    * Name of the collection where models are stored
    */
  val collectionName: String


  def collection()(implicit ec: ExecutionContext): Future[JSONCollection] = {
    db.map(_.collection[JSONCollection](collectionName))
  }


  /**
    * Insert a model into the collection
    *
    * @param model
    * @param ec
    * @param w Adding the implicit writer will allow concrete write definitions to override
    *          the [[BaseModel]] writer
    * @return
    */
  def insert(model: M)(implicit ec: ExecutionContext, w: OWrites[M]): Future[Boolean] = {
    val id = model.id

    Logger.info(s"Inserting model $id into $collectionName")

    collection
      .flatMap(_.insert(model))
      .map(_.ok)
  }


  /**
    * Upsert a model into the collection
    *
    * @param model
    * @param ec
    * @return
    */
  def upsert(model: M)(implicit ec: ExecutionContext): Future[Boolean] = {
    val id = model.id.toString
    val selector = Json.obj("id" -> id)
    val modifier = Json.obj("$set" -> Json.toJson[M](model))

    Logger.info(s"Upserting model $id into $collectionName")

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
    Logger.info(s"Deleting record ${modelId.value} from $collectionName")
    val selector = Json.obj("id" -> modelId.toString)

    collection
      .flatMap(_.remove(selector))
      .map(_.ok)
  }

}
