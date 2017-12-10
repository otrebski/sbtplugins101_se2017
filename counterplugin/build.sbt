import sbt.Keys.organization

lazy val `counterplugin` =
  (project in file("."))
    .enablePlugins(ScriptedPlugin)
    .settings(
      libraryDependencies ++= Seq(
        "com.lihaoyi" %% "scalatags" % "0.6.5",
        "org.scalatest" %% "scalatest" % "3.0.4" % "test"
      ),
      sbtPlugin := true,
      scalaVersion := "2.12.4",
      organization := "otrebski",
      name := "counter",
      version := "1.1-SNAPSHOT",
      publishMavenStyle := false,
      addSbtPlugin("com.typesafe.sbt" % "sbt-git" % "0.9.3")
    )
