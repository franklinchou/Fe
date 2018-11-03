package dao

import lib.containers.StringContainer
import lib.jsonapi.Resource
import models.AbstractModelId

import scala.concurrent.{ExecutionContext, Future}


/**
  * Abstract unstructured database access object
  */
trait UnstructuredDao[R <: Resource] {


  /**
    * Insert a record into the database.
    *
    * @param record
    * @return
    */
  def insert(record: R)(implicit ec: ExecutionContext): Future[Boolean]


  /**
    * Upsert a record into the database.
    *
    * @param record
    * @return
    */
  def upsert(record: Resource): Future[Boolean]


  /**
    * Remove a record from the database.
    *
    * @param id
    * @return
    */
  def delete(id: StringContainer[AbstractModelId]): Future[Boolean]

}
