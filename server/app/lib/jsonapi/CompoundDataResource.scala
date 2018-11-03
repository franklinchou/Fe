package lib.jsonapi


/**
  * Corresponds to Compound Documents
  * http://jsonapi.org/format/#document-compound-documents
  */
trait CompoundDataResource extends BaseResource {

  val data: Seq[DataResource]

  val included: List[DataResource]

}
