package services

import com.google.inject.Inject
import dao.mongo.ExerciseSessionRepo
import models.ExerciseSessionModel

import scala.concurrent.{ExecutionContext, Future}


class ReportingService @Inject()(exerciseSessionRepo: ExerciseSessionRepo)
                                (implicit ec: ExecutionContext) {


  /**
    * Find all the exercise sessions associated with a given user.
    *
    * @param user
    * @return
    */
  def findAll(user: UserId): Future[List[ExerciseSessionModel]] = {
    exerciseSessionRepo.find(user)
  }

}
