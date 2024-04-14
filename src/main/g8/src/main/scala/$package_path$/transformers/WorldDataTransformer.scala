package $package$.transformers

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.col
import org.apache.spark.sql.functions.lit
import org.apache.spark.sql.functions.row_number

import $package$.extractors.Extractor

private[transformers] class WorldDataTransformer(
    worldDataExtractor: Extractor,
    countriesExtractor: Extractor
) extends Transformer {

  override def transform(spark: SparkSession): DataFrame = {
    val worldDataDf = worldDataExtractor.extract(spark)
    val countriesDf = countriesExtractor.extract(spark)

    val regionWindow = Window.partitionBy("region").orderBy(col("tax_rate"))

    worldDataDf
      .as("data")
      .join(
        countriesDf.as("country"),
        col("data.country") === col("country.name"),
        "leftouter"
      )
      .where(col("region").isNotNull)
      .withColumn("score_by_region", row_number().over(regionWindow))
      .where(col("score_by_region") <= lit(10))
      .select("country", "region", "tax_rate")
  }
}

object WorldDataTransformer {
  def apply(worldDataExtractor: Extractor, countriesExtractor: Extractor): Transformer =
    new WorldDataTransformer(worldDataExtractor, countriesExtractor)
}
