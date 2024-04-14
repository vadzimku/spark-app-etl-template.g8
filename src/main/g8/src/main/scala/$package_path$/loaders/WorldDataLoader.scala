package $package$.loaders

import org.apache.spark.sql.DataFrame

import $package$.config.SparkEtlConfig

private[loaders] class WorldDataLoader(
    targetPath: Option[String],
    targetTable: Option[String],
    targetFormat: String
) extends Loader {
  override def load(resultDf: DataFrame): Unit =
    targetPath match {
      case Some(path) => resultDf.write.format(targetFormat).save(path)
      case None if targetTable.isDefined =>
        resultDf.write.format(targetFormat).saveAsTable(targetTable.get)
      case _ => resultDf.show(numRows = 100, truncate = false)
    }
}

object WorldDataLoader {
  def apply(config: SparkEtlConfig): Loader =
    new WorldDataLoader(config.targetPath, config.targetTable, config.sourceFormat)
}
