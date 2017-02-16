package extractors

import models.CDef
import models.CDef._
import scala.collection.JavaConversions._

import scala.language.experimental.macros
import scala.reflect.macros.whitebox

/**
  * Created by andreas on 2017/02/15.
  */

trait CDefExt[T <: CDef]{
  def codeOf(code: String): T
  def listAll(): List[T]
}

object CDefExt {
  def materialize[T <: CDef]: CDefExt[T] = macro materializeImpl[T]

  def materializeImpl[T <: CDef](c: whitebox.Context)(implicit tag:c.WeakTypeTag[T]): c.Tree = {
    import c.universe._
    q"""new CDefExt[Flg] {
           override def codeOf(code: String) = Flg.codeOf(code)
           override def listAll() = Flg.listAll().toList
        }
     """
  }
}

trait CDefExtractor[O <: CDef] extends Function1[Option[String], Option[O]] {
  implicit val cdefObj: CDefExt[O]
  override def apply(in: Option[String]): Option[O] = in
    .flatMap(inStr =>{
      val byCode = Option(cdefObj.codeOf(inStr))
      val byName = cdefObj.listAll().find(_.name().toLowerCase.replaceAll(" ", "") == inStr.toLowerCase.replaceAll(" ", ""))
      val byAlias = cdefObj.listAll().find(_.alias().toLowerCase.replaceAll(" ", "") == inStr.toLowerCase.replaceAll(" ", ""))
      byCode orElse byName orElse byAlias
    })

  def apply(in: Option[String], dummy:String = null)(implicit cdefObj:CDefExt[O]): Option[O] = apply(in)
}

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


