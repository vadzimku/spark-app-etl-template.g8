package $package$.extractors

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.Column
import org.apache.spark.sql.functions.coalesce
import org.apache.spark.sql.functions.col
import org.apache.spark.sql.functions.lit
import org.apache.spark.sql.types.DecimalType
import $package$.config.SparkEtlConfig

private[extractors] class WorldDataExtractor(sourcePath: String, sourceFormat: String)
    extends Extractor {

  override def extract(spark: SparkSession): DataFrame =
    adjustReaderOptions(
      sourceFormat,
      spark
        .read
        .format(sourceFormat)
    )
      .load(sourcePath)
      .select(
        col("Country").as("country"),
        col("Total tax rate").as("tax_rate_str"),
        percentStringToDecimal("Total tax rate").as("tax_rate")
      )
      .withColumn("tax_rate", coalesce(col("tax_rate"), lit(0.0)))

  private def percentStringToDecimal(column: String): Column = {
    import org.apache.spark.sql.functions.replace

    replace(col(column), lit("%"), lit("")).divide(lit(100.0)).cast(DecimalType(18, 4))
  }
}

object WorldDataExtractor {
  def apply(config: SparkEtlConfig): Extractor =
    new WorldDataExtractor(config.worldDataPath, config.sourceFormat)
}
