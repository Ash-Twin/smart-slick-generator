package me.liuchenyu

import me.liuchenyu.Imports.imports

object TypeMapping {
  def apply(sqlType: String): String =
    if (sqlType.startsWith("VARCHAR")) "String"
    else if (sqlType.startsWith("TIMESTAMP")) {
      if (!imports.contains("import java.sql.Timestamp")) {
        imports.addOne("import java.sql.Timestamp")
      }
      "Timestamp"
    } else if (sqlType.startsWith("DECIMAL")) "BigDecimal"
    else if( sqlType.endsWith("INT")) "Int"
    else sqlType
}
