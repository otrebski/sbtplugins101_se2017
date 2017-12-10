import sbt.Keys.organization

lazy val `counterplugin` =
  (project in file("."))
    .enablePlugins(ScriptedPlugin)
    .settings(
      libraryDependencies ++= Seq(
        "org.scalatest" %% "scalatest" % "3.0.4" % "test"
      ),
      sbtPlugin := true,
      scalaVersion := "2.12.4",
      organization := "otrebski",
      name := "counter",
      version := "1.0-SNAPSHOT",
      publishMavenStyle := false,
    )
