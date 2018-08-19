package dao


import lib.containers.StringContainer
import models.{AbstractModelId, Record}

import scala.concurrent.Future


/**
  * Abstract unstructured database access object
  */
trait UnstructuredDao[R <: Record] {


  /**
    * Insert a record into the database.
    *
    * @param record
    * @return
    */
  def create(record: Record): Future[Boolean]


  /**
    * Upsert a record into the database.
    *
    * @param record
    * @return
    */
  def upsert(record: Record): Future[Boolean]


  /**
    * Remove a record from the database.
    *
    * @param id
    * @return
    */
  def delete(id: StringContainer[AbstractModelId]): Future[Boolean]

}
