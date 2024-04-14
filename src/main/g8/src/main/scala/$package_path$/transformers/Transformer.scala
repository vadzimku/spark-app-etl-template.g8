package $package$.transformers

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.SparkSession

trait Transformer {
  def transform(spark: SparkSession): DataFrame
}
