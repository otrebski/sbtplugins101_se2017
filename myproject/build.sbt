name := "myproject"

scalaVersion := "2.12.4"

val count = taskKey[Unit]("Count maximum count of non-white chars in scala source file line")

lazy val library = (project in file("."))
  .settings(
    count := {
      println("----------------------------------")
      println("Running task")
      val maxLength = maximumNonWhiteCharsInScalaLine()
      val report = s"Maximum number of non-white chars in file: $maxLength"
      println(report)
      saveToFile("count.txt", report)
    }
  )


def maximumNonWhiteCharsInScalaLine(): Int = {
  import java.io.File
  import scala.io.Source
  def max(file: File): Int = if (file.isDirectory) {
    file.listFiles().map(max).toList match {
      case Nil => 0
      case list => list.max
    }
  } else if (file.getName.endsWith(".scala")) {
    Source.fromFile(file)
      .getLines()
      .map(_.replaceAll("\\s", "").length)
      .max
  } else 0
  max(new File("src"))
}

def saveToFile(filename:String, content:String) = {
  new java.io.PrintWriter(filename) {
    write(content)
    close()
  }
}