package services

import com.google.inject.Inject
import dao.`abstract`.SessionRepo
import lib.containers.StringContainer
import models.{AbstractUserId, SessionModel}

import scala.concurrent.{ExecutionContext, Future}


class ReportingService @Inject()(sr: SessionRepo)
                                (implicit ec: ExecutionContext) {


  /**
    * Find all the exercise sessions associated with a given user.
    *
    * @param user
    * @return
    */
  def findAll(user: StringContainer[AbstractUserId]): Future[List[SessionModel]] = sr.find(user)

}
