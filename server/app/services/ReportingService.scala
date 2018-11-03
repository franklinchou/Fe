package services

import com.google.inject.Inject
import dao.mongo.{MongoRepo, SessionRepo}
import models.SessionModel
import resources.SessionResource

import scala.concurrent.{ExecutionContext, Future}


class ReportingService @Inject()(sessionRepo: SessionRepo)
                                (implicit ec: ExecutionContext) {


  /**
    * Find all the exercise sessions associated with a given user.
    *
    * NOTE: This service inherits [[Future]] from ReactiveMongoApi see [[MongoRepo]]
    *
    * @param user
    * @return
    */
  def findAll(user: UserId): Future[List[SessionModel]] = {
    sessionRepo.find(user)
  }

}
