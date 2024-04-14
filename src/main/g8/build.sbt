ThisBuild / scalaVersion := "$scala_version$"
ThisBuild / organization := "$organization$"

enablePlugins(GitVersioning)

lazy val `$app_name$` = (project in file("."))
  .settings(
    scalacOptions ++= Seq("-deprecation"),
    javacOptions  ++= Seq("-source", "1.8", "-target", "1.8")
  )
  .settings(
    libraryDependencies ++= Seq(
      Libraries.Scopt,
      Libraries.DeltaLake    % Provided,
      Libraries.SparkSql     % Provided,
      Libraries.SparkTesting % Test
    )
  )
  .settings(assembly / assemblyJarName := s"\${name.value}-\${version.value}.jar")
