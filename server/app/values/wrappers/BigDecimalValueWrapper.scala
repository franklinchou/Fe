package values.wrappers

import play.api.libs.json.{Format, JsNumber, Reads}


object BigDecimalValueWrapper {
  implicit def jsonFormat[A]: Format[BigDecimalValueWrapper[A]] = {
    Format(
      Reads.bigDecReads.map(BigDecimalValueWrapper.apply[A]),
      (w: BigDecimalValueWrapper[A]) => JsNumber(w.wrapped)
    )
  }
}

case class BigDecimalValueWrapper[A](wrapped: BigDecimal) extends AnyVal