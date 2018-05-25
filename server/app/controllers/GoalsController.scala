package controllers

import com.google.inject.Inject
import dao.GlobalMongoRepositoryConf
import models.GoalModel
import play.api.Logger
import play.api.libs.json.{JsObject, JsValue}
import play.api.mvc._
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.api.Cursor.FailOnError
import reactivemongo.api.{Cursor, DefaultDB, ReadPreference}
import reactivemongo.play.json.collection.JSONCollection

import scala.concurrent.{ExecutionContext, Future}

/**
  * Use to serialize data between JSON & custom model
  */
import reactivemongo.play.json.JsObjectDocumentWriter


class GoalsController @Inject()(cc: ControllerComponents,
                                reactiveMongoApi: ReactiveMongoApi) extends AbstractController(cc) {

  implicit def ec: ExecutionContext = cc.executionContext

  protected def database: Future[DefaultDB] = reactiveMongoApi.database

  def collection: Future[JSONCollection] = reactiveMongoApi.database.map(_.collection[JSONCollection]("goals"))

  private final val jsonHeader = "Content-Type" -> "application/json"

  private final val rp = GlobalMongoRepositoryConf.readPreference

  /**
    * Write to database from JSON
    *
    * @return
    */
  def createFromJson: Action[JsValue] = Action.async(parse.json) { request =>
    request.body.validate[GoalModel].map { g =>
      collection.flatMap(_.insert(g)) map { e =>
        Logger.debug(s"Successfully inserted with status = $e")
        Created
      }
    }.getOrElse(Future.successful(BadRequest))
  }


  /**
    * Find in database with `query`
    *
    * http://reactivemongo.org/releases/0.11/documentation/tutorial/play2.html
    *
    * https://github.com/arturopala/play-2.4-crud-with-reactive-mongo/blob/master/app/services/CRUDService.scala
    *
    * @param query
    * @param limit
    * @return
    */
  private def find(query: JsObject, limit: Int): Action[AnyContent] = Action.async {
    val cursors = collection.map( c => c.find(query).cursor[GoalModel](rp))

    cursors.flatMap(c => c.collect[List](limit, FailOnError[List[GoalModel]]())).map { g =>
      Ok // List[GoalModel]
    }
  }


  /**
    * Find all items matching `query`
    *
    * @param query
    * @return
    */
  def findAll(query: JsObject): Action[AnyContent] = find(query, -1)



  /**
    * Find a single record matching `query`
    *
    * @param query
    * @return
    */
  def find(query: JsObject): Action[AnyContent] = find(query, 1)


}
