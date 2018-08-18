package lib.containers

import play.api.libs.functional.syntax._
import play.api.libs.json._

object StringContainer {


  /**
    * Format json
    *
    * @tparam A
    * @return
    */
  implicit def jsFormat[A]: Format[StringContainer[A]] = Format(
    Reads.StringReads.map(StringContainer.apply[A]),
    Writes.StringWrites.contramap(_.id)
  )

}


final case class StringContainer[A](id: String) extends AnyVal {

  override def toString: String = id

}