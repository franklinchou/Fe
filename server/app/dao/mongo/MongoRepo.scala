package dao.mongo

import dao.UnstructuredDao
import lib.containers.StringContainer
import models.{AbstractModel, AbstractModelId}
import play.api.Logger
import play.api.libs.json.Json
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.play.json._
import reactivemongo.play.json.collection.JSONCollection

import scala.concurrent.{ExecutionContext, Future}

trait MongoRepo[M <: AbstractModel] extends UnstructuredDao[M] {


  val rma: ReactiveMongoApi


  /**
    * Database
    */
  val db = rma.database


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
    * @return
    */
  def create(model: M)(implicit ec: ExecutionContext): Future[Boolean] = {
    Logger.info(s"Inserting record ${model.id.toString} into $collectionName")

    collection
      .flatMap(_.insert(model.toJson))
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
    val modifier = Json.obj("$set" -> model.toJson)

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
