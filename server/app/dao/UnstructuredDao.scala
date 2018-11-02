package dao

import lib.containers.StringContainer
import models.{AbstractModelId, AbstractModel}

import scala.concurrent.Future


/**
  * Abstract unstructured database access object
  */
trait UnstructuredDao[R <: AbstractModel] {


  /**
    * Insert a record into the database.
    *
    * @param record
    * @return
    */
  def insert(record: R): Future[Boolean]


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
