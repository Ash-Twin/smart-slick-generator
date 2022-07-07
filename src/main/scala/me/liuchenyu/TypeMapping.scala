package me.liuchenyu

import me.liuchenyu.Imports.imports

object TypeMapping {
  def apply(sqlType: String, nullable: Boolean = false): String =
    if (sqlType.startsWith("VARCHAR")) wrapNullable("String", nullable)
    else if (sqlType.startsWith("TIMESTAMP")) {
      if (!imports.contains("import java.sql.Timestamp")) {
        imports.addOne("import java.sql.Timestamp")
      }
      wrapNullable("Timestamp", nullable)
    } else if (sqlType.startsWith("DECIMAL")) wrapNullable("BigDecimal", nullable)
    else if (sqlType.endsWith("INT")) wrapNullable("Int", nullable)
    else if (sqlType.endsWith("TEXT")) wrapNullable("String", nullable)
    else wrapNullable(sqlType, nullable)

  def wrapNullable(str: String, nullable: Boolean): String =
    if (nullable)
      "Option[" + str + "]"
    else
      str
}
