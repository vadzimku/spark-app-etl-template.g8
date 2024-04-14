// See http://www.foundweekends.org/giter8/testing.html#Using+the+Giter8Plugin for more details
ThisBuild / scalaVersion := "2.12.18"

lazy val root = (project in file(".")).settings(
  name := "spark-app-etl-template",
  Test / test := {
    val _ = (Test / g8Test).toTask("").value
  })
