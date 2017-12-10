package otrebski

import java.io.File

import otrebski.CounterPlugin.autoImport
import sbt.{Command, Project}

object CounterCommand {

  def count = Command.command("count") { state =>

    val baseName = Project.extract(state).get(autoImport.countFileBaseName)
    val max = Counter.max(new File("src"))
    val msg = s"Max non-white chars in line: $max"
    val file = s"$baseName.txt"
    state.log.info(msg)
    state.log.info(s"Saving status to $file")
    saveToFile(file, msg)
    state
  }


  private def saveToFile(file: String, msg: String) = {
    new java.io.PrintWriter(file) {
      write(msg)
      close()
    }
  }
}
