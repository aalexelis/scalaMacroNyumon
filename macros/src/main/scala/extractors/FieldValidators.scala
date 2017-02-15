package extractors

import models.CDef
import models.CDef._
import scala.collection.JavaConversions._


/**
  * Created by andreas on 2017/02/15.
  */

trait CDefExt[T <: CDef]{
  def codeOf(code: String): T
  def listAll(): List[T]
}

class CDefExtractor[O <: CDef](cdefObj: CDefExt[O]) extends Function1[Option[String], Option[O]] {
  override def apply(in: Option[String]): Option[O] = in
    .flatMap(inStr =>{
      val byCode = Option(cdefObj.codeOf(inStr))
      val byName = cdefObj.listAll().find(_.name().toLowerCase.replaceAll(" ", "") == inStr.toLowerCase.replaceAll(" ", ""))
      val byAlias = cdefObj.listAll().find(_.alias().toLowerCase.replaceAll(" ", "") == inStr.toLowerCase.replaceAll(" ", ""))
      byCode orElse byName orElse byAlias
    })
}

object FlgExtractor extends CDefExtractor(new CDefExt[Flg] {
  override def codeOf(code: String) = Flg.codeOf(code)
  override def listAll() = Flg.listAll().toList
})

object GenderExtractor extends CDefExtractor(new CDefExt[Gender] {
  override def codeOf(code: String) = Gender.codeOf(code)
  override def listAll() = Gender.listAll().toList
})

//...

