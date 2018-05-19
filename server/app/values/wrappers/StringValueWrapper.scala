package values.wrappers

import play.api.libs.functional.syntax.toContraFunctorOps
import play.api.libs.json.{Format, Reads, Writes}


object StringValueWrapper {
  implicit def jsonFormat[A]: Format[StringValueWrapper[A]] = {
    Format(
      Reads.StringReads.map(StringValueWrapper.apply[A]),
      Writes.StringWrites.contramap(_.wrapped)
    )
  }
}


case class StringValueWrapper[A](wrapped: String) extends AnyVal
