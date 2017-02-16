package extractors

import models.CDef

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
    val tpe = weakTypeOf[T]
    val con = tpe.typeSymbol.companion
    val test = reify({def codeOf(code:String) = models.CDef.Flg.codeOf(code)}).tree
    println(showRaw(test))

    val result = q"""new CDefExt[$tpe] {
           override def codeOf(code: String) = $con.codeOf(code)
           override def listAll() = $con.listAll().toList
        }
     """
    println(showRaw(result))
    result
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


