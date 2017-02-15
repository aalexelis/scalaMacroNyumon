package extractors

import models.CDef
import models.CDef._
import scala.collection.JavaConversions._


/**
  * Created by andreas on 2017/02/15.
  */

trait CDefExtractor[O <: CDef] extends Function1[Option[String], Option[O]] {
  override def apply(in: Option[String]): Option[O] = in
    .flatMap(inStr =>{
      val byCode: Option[O] = ???
      val byName: Option[O] = ???
      val byAlias: Option[O] = ???
      byCode orElse byName orElse byAlias
    })
}

object FlgExtractor extends CDefExtractor[Flg]

object GenderExtractor extends CDefExtractor[Gender]

//...

