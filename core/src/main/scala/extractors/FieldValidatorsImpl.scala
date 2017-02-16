package extractors

import models.CDef._
import scala.collection.JavaConversions._


/**
  * Created by andreas on 2017/02/16.
  */
object FlgExtractor extends CDefExtractor[Flg] {
  implicit val cdefObj:CDefExt[Flg] = new CDefExt[Flg] {
    override def codeOf(code: String) = Flg.codeOf(code)
    override def listAll() = Flg.listAll().toList
  }
}

object GenderExtractor extends CDefExtractor[Gender] {
  implicit val cdefObj: CDefExt[Gender] = new CDefExt[Gender] {
    override def codeOf(code: String) = Gender.codeOf(code)
    override def listAll() = Gender.listAll().toList
  }
}

//...

object MacroFlgExtractor extends CDefExtractor[Flg] {
  implicit val cdefObj: CDefExt[Flg] = CDefExt.materialize[Flg]
}

object MacroGenderExtractor extends CDefExtractor[Gender] {
  implicit val cdefObj: CDefExt[Gender] = CDefExt.materialize[Gender]
}
