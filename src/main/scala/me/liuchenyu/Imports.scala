package me.liuchenyu

import scala.collection.mutable

object Imports {
  var imports: mutable.ListBuffer[String] = mutable.ListBuffer(
    "import io.gdmexchange.common.util.TableBase",
    "import slick.jdbc.MySQLProfile.api._",
    "import slick.lifted.Rep",
    "import java.sql.Timestamp",
    "import java.sql.Date"
  )
  def importing: String                   = imports.mkString("\n")

}
