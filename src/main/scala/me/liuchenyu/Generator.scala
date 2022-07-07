package me.liuchenyu

import com.google.common.base.CaseFormat
import io.debezium.connector.mysql.antlr.MySqlAntlrDdlParser
import io.debezium.relational.{TableId, Tables}

import java.io.{BufferedWriter, File, FileWriter}
import scala.io.Source
import scala.util.{Failure, Success, Try, Using}

object Generator {
  def underscoreToCamel(name: String) = "_([a-z\\d])".r.replaceAllIn(name, m => m.group(1).toUpperCase())
  def main(args: Array[String]): Unit = {
    val optionMap = ArgsParser(args).apply
    val file      = optionMap("-f")
    val format    = optionMap.getOrElse("-t", "default")
    val parser    = new MySqlAntlrDdlParser();
    val tables    = new Tables()
    val lines     = Using(Source.fromFile(file))(source => source.mkString)
    lines match {
      case Success(value)     =>
        parser.parse(value, tables)
        tables.tableIds().forEach { id =>
          val table = tables.forTable(id)
          println(table)
          val gFile = TableParser(table, ConvertStrategy.matchConfig(format)).build
          val file  = new File(s"${underscoreToCamel(table.id.toString.split("\\.").last).capitalize}.scala")
          val bw    = new BufferedWriter(new FileWriter(file))
          bw.write(gFile)
          bw.close()
        }
      case Failure(exception) =>
        exception.printStackTrace()
    }
  }

}
