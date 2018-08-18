package models

import play.api.libs.functional.syntax._
import play.api.libs.json._

object Id {

  implicit def jsFormat[A]: Format[Id[A]] = Format(
    Reads.StringReads.map(Id.apply[A]),
    Writes.StringWrites.contramap(_.id)
  )

}


final case class Id[A](id: String) extends AnyVal {

  override def toString: String = id
  
}