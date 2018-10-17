package services

import com.google.inject.Inject
import dao.mongo.{ExerciseSessionRepo, MongoRepo}
import models.SessionModel

import scala.concurrent.{ExecutionContext, Future}


class ReportingService @Inject()(exerciseSessionRepo: ExerciseSessionRepo)
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
    exerciseSessionRepo.find(user)
  }

}
