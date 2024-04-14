package $package$.config

case class $class_name$Config(
    worldDataPath: String = "",
    countriesPath: String = "",
    sourceFormat: String = "csv",
    targetPath: Option[String] = None,
    targetTable: Option[String] = None,
    targetFormat: String = "delta"
)

object $class_name$Config {
  import scopt.OParser

  def load(args: Array[String]): SparkEtlConfig = {
    val builder = OParser.builder[SparkEtlConfig]
    val argsParser = {
      import builder._

      OParser.sequence(
        opt[String]('w', "world-data-path")
          .action((s, c) => c.copy(worldDataPath = s))
          .text("World data path")
          .required(),
        opt[String]('c', "countries-path")
          .action((s, c) => c.copy(countriesPath = s))
          .text("Countries path")
          .required(),
        opt[String]('f', "source-format")
          .action((s, c) => c.copy(sourceFormat = s))
          .text("Source format"),
        opt[String]('o', "target-path")
          .action((s, c) => c.copy(targetPath = Some(s)))
          .text("Target path"),
        opt[String]('t', "target-table")
          .action((s, c) => c.copy(targetTable = Some(s)))
          .text("Target table"),
        opt[String]('d', "target-format")
          .action((s, c) => c.copy(targetFormat = s))
          .text("Target format")
      )
    }

    OParser.parse(argsParser, args, SparkEtlConfig()) match {
      case Some(c) => c
      case _       => throw new IllegalArgumentException("Input parameters could not be parsed")
    }
  }
}
