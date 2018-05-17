package values.wrappers

import java.time.LocalDate

import play.api.libs.functional.syntax.toContraFunctorOps
import play.api.libs.json.{Format, Reads, Writes}


object DateValueWrapper {
  implicit def jsonFormat[A]: Format[DateValueWrapper[A]] = {
    Format(
      Reads.DefaultLocalDateReads.map(DateValueWrapper.apply[A]),
      Writes.DefaultLocalDateWrites.contramap(_.wrapped)
    )
  }
}

case class DateValueWrapper[A](wrapped: LocalDate) extends AnyVal