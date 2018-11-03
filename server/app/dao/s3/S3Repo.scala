package dao.s3

import java.io.ByteArrayInputStream

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.{DeleteObjectRequest, ObjectMetadata}
import dao.UnstructuredDao
import lib.jsonapi.Resource
import models.AbstractModelId
import play.api.Logger
import play.api.libs.json.Json

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success, Try}


trait S3Repo[M <: Resource] extends UnstructuredDao[M] {


  val bucket: String


  val db: AmazonS3


  /**
    * Create a new record (simply call upsert)
    *
    * @param model
    * @return
    */
  def create(model: M)(implicit ec: ExecutionContext): Future[Boolean] = upsert(model)


  /**
    * Upsert a record into the bucket.
    *
    * @param model
    * @return
    */
  def upsert(model: M)(implicit ec: ExecutionContext): Future[Boolean] = {

    val key = model.id
    val json = model.toJsonApi
    val bytes = Json.toBytes(json)
    val stream = new ByteArrayInputStream(bytes)

    Logger.info(s"Inserting record $key into $bucket")

    Try(db.putObject(bucket, key, stream, new ObjectMetadata())) match {
      case Success(_) => Future { true }
      case Failure(e) =>
        Logger.error(e.toString)
        Future { false }
    }
  }


  /**
    * Given a model id, delete that model from the bucket.
    *
    * @param id
    * @return
    */
  def delete(id: AbstractModelId)(implicit ec: ExecutionContext): Future[Boolean] = {

    val key = id.toString

    Logger.info(s"Deleting record $key from $bucket")

    Try(db.deleteObject(new DeleteObjectRequest(bucket, key))) match {
      case Success(_) => Future { true }
      case Failure(e) =>
        Logger.error(e.toString)
        Future { false }
    }
  }
}
