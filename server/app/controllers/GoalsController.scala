package controllers

import akka.util.Timeout
import com.google.inject.Inject
import models.GoalModel
import play.api.Logger
import play.api.libs.json.JsValue
import play.api.mvc.{AbstractController, Action, ControllerComponents}
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.api.DefaultDB
import reactivemongo.play.json.collection.JSONCollection

import scala.concurrent.{ExecutionContext, Future}

class GoalsController @Inject()(cc: ControllerComponents,
                                reactiveMongoApi: ReactiveMongoApi) extends AbstractController(cc) {

  implicit def ec: ExecutionContext = cc.executionContext

  protected def database: Future[DefaultDB] = reactiveMongoApi.database

  def collection: Future[JSONCollection] = reactiveMongoApi.database.map(_.collection[JSONCollection]("goals"))


  private final val jsonHeader = "Content-Type" -> "application/json"


  /**
    * Write to database from JSON
    *
    * @return
    */
  def createFromJson: Action[JsValue] = Action.async(parse.json) { request =>

    if (request.headers.headers.contains(jsonHeader)) {
      request.body.validate[GoalModel].map { g =>
        collection.flatMap(_.insert(g)) map { e =>
          Logger.debug(s"Successfully inserted with error = $e")
          Created
        }
      }.getOrElse(Future.successful(BadRequest))
    } else {
      Future.successful(BadRequest("Input format improper"))
    }
  }


}
