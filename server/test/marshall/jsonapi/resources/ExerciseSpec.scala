package marshall.jsonapi.resources

import lib.jsonapi.ExerciseJsonApi
import models.exercises.BenchPress
import org.scalatest.FunSpec

class ExerciseSpec extends FunSpec {

  describe("Exercise field") {
    it ("should marshall Exercise object -> jsonapi string") {
      assert(ExerciseJsonApi(BenchPress) == "bench-press")
    }
  }

}
