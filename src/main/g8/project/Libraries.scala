import sbt.*

object Libraries {
  private object Versions {
    val DeltaVersion        = "$delta_lake_version$"
    val ScoptVersion        = "$scopt_version$"
    val SparkVersion        = "$spark_version$"
    val SparkTestingVersion = "$spark_version$_$spark_testing_base_version_suffix$"
  }

  val DeltaLake    = "io.delta"         %% "delta-spark"        % Versions.DeltaVersion
  val Scopt        = "com.github.scopt" %% "scopt"              % Versions.ScoptVersion
  val SparkSql     = "org.apache.spark" %% "spark-sql"          % Versions.SparkVersion
  val SparkTesting = "com.holdenkarau"  %% "spark-testing-base" % Versions.SparkTestingVersion
}
