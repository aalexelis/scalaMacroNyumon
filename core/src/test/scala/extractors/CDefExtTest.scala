package extractors

import models.CDef
import models.CDef._
import org.scalatest.FunSuite

/**
  * Created by andreas on 2017/02/15.
  */
class CDefExtTest extends FunSuite {

  test("FlgExtractor should extract Y properly"){
    val in = Option("Y")

    assert(FlgExtractor(in) == Option(Flg.True))
  }

}
