package dao.s3

import java.io.ByteArrayInputStream

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.{DeleteObjectRequest, ObjectMetadata}
import dao.UnstructuredDao
import models.{Record, AbstractModelId}
import play.api.Logger
import play.api.libs.json.Json

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success, Try}

trait S3Repo[R <: Record] extends UnstructuredDao {


  val bucket: String


  val db: AmazonS3


  /**
    * Create a new record (simply call upsert)
    *
    * @param record
    * @return
    */
  def create(record: R)(implicit ec: ExecutionContext): Future[Boolean] = upsert(record)


  /**
    * Upsert a record into the bucket.
    *
    * @param record
    * @return
    */
  def upsert(record: R)(implicit ec: ExecutionContext): Future[Boolean] = {

    val key = record.id.toString
    val json = record.toJson
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
    * Given a record id, delete that record from the bucket.
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
