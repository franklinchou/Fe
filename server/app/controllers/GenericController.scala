package controllers

import play.api.mvc.{AbstractController, ControllerComponents}
import services.GenericService

class GenericController(cc: ControllerComponents,
                        service: GenericService) extends AbstractController(cc) {


}
