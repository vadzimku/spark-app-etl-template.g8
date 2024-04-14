package $package$.config

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class $class_name$ConfigTest extends AnyFunSuite {
  test("$class_name$Config is loaded correctly (default)") {
    val cliArgs =
      Array("--world-data-path", "/path/to/world/data/", "--countries-path", "/path/to/countries/")
    val config = $class_name$Config.load(cliArgs)

    config.worldDataPath shouldBe "/path/to/world/data/"
    config.countriesPath shouldBe "/path/to/countries/"
    config.sourceFormat shouldBe "csv"
    config.targetPath shouldBe None
    config.targetTable shouldBe None
    config.targetFormat shouldBe "delta"
  }
}