package utils

import scala.io.Source

object FileReader {

  /**
    * Load data from a given `fileName` into a string
    *
    * @param fileName
    * @return
    */
  def loadToString(fileName: String): String = {
    val bufferedSource = Source.fromResource(fileName)
    val contents = bufferedSource.getLines.mkString
    bufferedSource.close()
    contents
  }


}
