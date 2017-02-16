package extractors

import models.CDef
import models.CDef._
import org.scalatest.FunSuite

/**
  * Created by andreas on 2017/02/15.
  */
class CDefExtTest extends FunSuite {

  test("FlgExtractor should reject garbage"){
    val in = Option("garbage")

    assert(FlgExtractor(in).isEmpty)
  }

  test("FlgExtractor should extract Y properly"){
    val in = Option("Y")

    assert(FlgExtractor(in) == Option(Flg.True))
  }

  test("GenderExtractor should reject garbage"){
    val in = Option("garbage")

    assert(GenderExtractor(in).isEmpty)
  }

  test("GenderExtractor should extract Male properly"){
    val in = Option("Male")

    assert(GenderExtractor(in) == Option(Gender.Male))
  }



}
