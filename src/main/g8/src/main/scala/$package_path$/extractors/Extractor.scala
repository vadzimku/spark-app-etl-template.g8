package $package$.extractors

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.DataFrameReader
import org.apache.spark.sql.SparkSession

trait Extractor {
  def extract(spark: SparkSession): DataFrame

  protected def adjustReaderOptions(
      sourceFormat: String,
      dfReader: DataFrameReader
  ): DataFrameReader =
    sourceFormat match {
      case "csv" => dfReader.option("header", value = true)
      case _     => dfReader
    }
}
