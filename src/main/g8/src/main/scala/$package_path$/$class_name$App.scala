package $package$

import org.apache.spark.sql.SparkSession

import $package$.config.SparkEtlConfig
import $package$.extractors.CountriesExtractor
import $package$.extractors.WorldDataExtractor
import $package$.loaders.WorldDataLoader
import $package$.transformers.WorldDataTransformer

object SparkEtlApp {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession =
      SparkSession
        .builder()
        .appName("$app_name$")
        .enableHiveSupport()
        .getOrCreate()

    val config = $class_name$Config.load(args)

    val worldDataTransformer = WorldDataTransformer(
      WorldDataExtractor.apply(config),
      CountriesExtractor.apply(config)
    )
    val worldDataLoader = WorldDataLoader(config)

    worldDataLoader.load {
      worldDataTransformer.transform(spark)
    }
  }
}
