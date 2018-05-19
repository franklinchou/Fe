package values.wrappers

import play.api.libs.functional.syntax.toContraFunctorOps
import play.api.libs.json.{Format, Reads, Writes}


object IntValueWrapper {
  implicit def jsonFormat[A]: Format[IntValueWrapper[A]] = {
    Format(
      Reads.IntReads.map(IntValueWrapper.apply[A]),
      Writes.IntWrites.contramap(_.wrapped)
    )
  }
}


case class IntValueWrapper[A](wrapped: Int) extends AnyVal