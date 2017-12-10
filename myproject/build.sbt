name := "myproject"

scalaVersion := "2.12.4"


lazy val library = (project in file("."))
  .enablePlugins(CounterPlugin)

countFileBaseName := "custom_stats_file"

