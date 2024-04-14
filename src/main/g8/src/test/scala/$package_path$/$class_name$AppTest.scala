package $package$

import com.holdenkarau.spark.testing.DatasetSuiteBase

import org.scalatest.BeforeAndAfter
import org.scalatest.funsuite.AnyFunSuite

class $class_name$AppTest extends AnyFunSuite with DatasetSuiteBase with BeforeAndAfter {

  private val testSchema = "test"
  private val targetTable = s"\$testSchema.test_table_output"
  private val countriesPath = getClass.getResource("/countries.csv").getPath
  private val worldDataPath = getClass.getResource("/world-data-2023.csv").getPath

  before {
    spark.sql(s"CREATE SCHEMA \$testSchema")
  }

  after {
    spark.sql(s"DROP SCHEMA \$testSchema CASCADE")
  }

  test("SparkEtlApp output is correct") {
    $class_name$App.main {
      Array(
        "--world-data-path",
        worldDataPath,
        "--countries-path",
        countriesPath,
        "--target-table",
        targetTable
      )
    }

    val resultDf = spark.table("test.test_table_output")

    assert("Number of loaded records is incorrect", 50, resultDf.count())
  }
}
