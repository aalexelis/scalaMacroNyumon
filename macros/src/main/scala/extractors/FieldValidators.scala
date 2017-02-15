package extractors

import models.CDef
import models.CDef._
import scala.collection.JavaConversions._


/**
  * Created by andreas on 2017/02/15.
  */

class CDefExtractor[O <: CDef](cdefObj: O) extends Function1[Option[String], Option[O]] {
  override def apply(in: Option[String]): Option[O] = in
    .flatMap(inStr =>{
      val byCode = Option(cdefObj.codeOf(inStr))
      val byName = cdefObj.listAll().find(_.name().toLowerCase.replaceAll(" ", "") == inStr.toLowerCase.replaceAll(" ", ""))
      val byAlias = cdefObj.listAll().find(_.alias().toLowerCase.replaceAll(" ", "") == inStr.toLowerCase.replaceAll(" ", ""))
      byCode orElse byName orElse byAlias
    })
}

object FlgExtractor extends CDefExtractor(Flg)

object GenderExtractor extends CDefExtractor(Gender)

//...

