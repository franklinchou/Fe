package dao

import containers.StringContainer
import models.{AbstractModel, AbstractModelId}

import scala.concurrent.Future


/**
  * Abstract unstructured database access object
  */
trait UnstructuredDao {


  /**
    * Insert a record into the database.
    *
    * @param record
    * @return
    */
  def create(record: AbstractModel): Future[Boolean]


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
