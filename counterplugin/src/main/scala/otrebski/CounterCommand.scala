package otrebski

import java.io.File

import otrebski.CounterPlugin.autoImport
import sbt.{Command, Project, State}

object CounterCommand {

  def count = Command.command("count") { state =>
    process(state, html = false)
  }

  def countHtml = Command.command("countHtml") { state =>
    process(state, html = true)
  }

  def process(state: State, html: Boolean): State = {
    val baseName = Project.extract(state).get(autoImport.countFileBaseName)

    val max = Counter.max(new File("src"))
    val commitMessage: Option[String] = Git().currentId().toOption.map(commitHah => s"Git commit hash: $commitHah")
    val msg = if (html) {
      val projectName: String = Project.extract(state).get(sbt.Keys.name)
      Html.format(projectName, max, commitMessage)
    } else {
      s"Max non-white chars in line: $max. $commitMessage"
    }
    val file = if (html) s"$baseName.html" else s"$baseName.txt"
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
