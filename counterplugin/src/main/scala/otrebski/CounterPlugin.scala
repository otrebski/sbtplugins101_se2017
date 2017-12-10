package otrebski

import sbt._

object CounterPlugin extends AutoPlugin {

  object autoImport {
    val countFileBaseName: SettingKey[String] = settingKey[String]("Count file base name")
  }

  import autoImport._

  override lazy val projectSettings = Seq(
    Keys.commands += CounterCommand.count,
    Keys.commands += CounterCommand.countHtml,
    countFileBaseName := "stats"
  )
}
