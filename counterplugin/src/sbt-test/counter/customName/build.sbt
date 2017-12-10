lazy val library = (project in file("."))
  .enablePlugins(CounterPlugin)

name := "basic"

scalaVersion := "2.12.4"

countFileBaseName := "custom_stats_file"



