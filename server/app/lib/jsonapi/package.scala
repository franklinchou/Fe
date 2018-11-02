package lib

import play.api.libs.json.JsObject

package object jsonapi {

  /**
    * Take a list of `Option[JsObject]` bound to a resource and produce
    * JsObject with only the populated fields appended to a given `default`
    * JsObject.
    *
    * @param m
    * @param default
    * @return
    */
  def reduce(m: Map[String, Option[JsObject]], default: JsObject): JsObject = {
    val definedOnly: Map[String, JsObject] =
      m.filter(_._2.isDefined).map(m => m._1 -> m._2.get)

    definedOnly.foldLeft(default) { (acc, pair) =>
      acc + pair
    }
  }

}
