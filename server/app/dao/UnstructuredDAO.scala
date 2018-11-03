package dao

import lib.containers.StringContainer
import lib.jsonapi.BaseResource
import models.{AbstractModelId, BaseModel}
import play.api.libs.json.OWrites

import scala.concurrent.{ExecutionContext, Future}


/**
  * Abstract unstructured database access object
  */
trait UnstructuredDAO[M <: BaseModel] {


  /**
    * Insert a model into the database.
    *
    * @param model
    * @return
    */
  def insert(model: M)(implicit ec: ExecutionContext, w: OWrites[M]): Future[Boolean]


  /**
    * Upsert a model into the database.
    *
    * @param model
    * @return
    */
  def upsert(model: BaseResource): Future[Boolean]


  /**
    * Remove a model from the database.
    *
    * @param id
    * @return
    */
  def delete(id: StringContainer[AbstractModelId]): Future[Boolean]

}
