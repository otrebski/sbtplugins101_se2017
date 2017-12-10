package otrebski

import java.io.File

import scala.io.{BufferedSource, Source}

object Counter {

  def max(file: File): Int = if (file.isDirectory) {
    file.listFiles().map(max).toList match {
      case Nil => 0
      case list => list.max
    }
  } else if (file.getName.endsWith(".scala")) {
    max(Source.fromFile(file))
  } else 0

  private def max(source: BufferedSource) = {
    source
      .getLines()
      .map(countNotWhite)
      .max
  }

  def countNotWhite(string: String): Int = string.replaceAll("\\s", "").length
}
