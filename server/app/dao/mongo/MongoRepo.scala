package dao.mongo

import dao.UnstructuredDao
import lib.containers.StringContainer
import lib.jsonapi.Resource
import models.AbstractModelId
import play.api.Logger
import play.api.libs.json.Json
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.api.DefaultDB
import reactivemongo.play.json._
import reactivemongo.play.json.collection.JSONCollection

import scala.concurrent.{ExecutionContext, Future}

trait MongoRepo[R <: Resource] extends UnstructuredDao[R] {


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
    * @return
    */
  def insert(model: R)(implicit ec: ExecutionContext): Future[Boolean] = {
    val id = model.id
    val json = model.toJsonApi

    Logger.info(s"Inserting model $id into $collectionName")

    collection
      .flatMap(_.insert(json))
      .map(_.ok)
  }


  /**
    * Upsert a model into the collection
    *
    * @param model
    * @param ec
    * @return
    */
  def upsert(model: R)(implicit ec: ExecutionContext): Future[Boolean] = {
    val id = model.id.toString
    val selector = Json.obj("id" -> id)
    val modifier = Json.obj("$set" -> model.toJsonApi)

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
