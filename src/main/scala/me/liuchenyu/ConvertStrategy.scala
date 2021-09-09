package me.liuchenyu

import com.google.common.base.CaseFormat

object ConvertStrategy {
  def Snake2Camel: String => String              = str => {
    CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, str)
  }
  def matchConfig(str: String): String => String = str.toLowerCase match {
    case "snake2camel" => ConvertStrategy.Snake2Camel
    case "default"     => x => x
  }
}
