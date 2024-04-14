package $package$.extractors

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.SparkSession
import $package$.config.SparkEtlConfig

private[extractors] class CountriesExtractor(sourcePath: String, sourceFormat: String)
    extends Extractor {

  override def extract(spark: SparkSession): DataFrame = adjustReaderOptions(
    sourceFormat,
    spark
      .read
      .format(sourceFormat)
  )
    .load(sourcePath)
    .select("name", "region")
}

object CountriesExtractor {
  def apply(config: SparkEtlConfig): Extractor =
    new CountriesExtractor(config.countriesPath, config.sourceFormat)
}
