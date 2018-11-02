package dao

import lib.containers.StringContainer
import models.{AbstractModel, AbstractModelId}

import scala.concurrent.{ExecutionContext, Future}


/**
  * Abstract unstructured database access object
  */
trait UnstructuredDao[M <: AbstractModel] {


  /**
    * Insert a record into the database.
    *
    * @param record
    * @return
    */
  def insert(record: M)(implicit ec: ExecutionContext): Future[Boolean]


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
