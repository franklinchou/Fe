package dao

import reactivemongo.api.ReadPreference

object GlobalMongoRepositoryConf {

  val readPreference: ReadPreference = ReadPreference.primaryPreferred

}
