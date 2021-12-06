package me.liuchenyu

import io.debezium.relational.{Column, Table}
import me.liuchenyu.Imports.imports

import java.util
import scala.collection.mutable.ListBuffer
import scala.jdk.CollectionConverters.CollectionHasAsScala

case class TableParser(table: Table, convert: String => String = str => str) {
    val tableName: String = table.id().toString + "Table"
  def caseClassBuilder: String = {
    val caseClassHead    = s"case class ${table.id().toString}(\n"
    val caseClassColumns = columnBuilder(table.columns())
    val caseClassTail    = s"\n)"
    caseClassHead + caseClassColumns + caseClassTail
  }
  def columnBuilder(columns: util.List[Column]): String = {
    val columnStr = ListBuffer.empty[String]
    columns.forEach { column =>
      val key        = convert(column.name())
      val nativeName = wrapType(column)
      columnStr addOne s"\t$key:$nativeName"
    }
    columnStr.mkString(",\n")
  }
  def wrapType(column:Column): String ={
    val nativeName = TypeMapping.apply(column.typeName())
    val value      = if (!column.isOptional) {
      nativeName
    } else s"Option[$nativeName]"
    value
  }
  def objectTableBuilder: String = {
    s"object $tableName extends TableBase[$tableName] {\n" +
      s"\toverride val tableName: String = \"$tableName\"\n" +
      s"\tlazy val table = new TableQuery(tag => new $tableName(tag))\n" +
      s"\tdef apply(): TableQuery[$tableName] = table\n" +
      "}"
  }


  def classTableBuilder: String = {
    val classTableHead = s"class $tableName(tag: Tag)\n    extends Table[${table.id().toString}](tag, Some(\"\"), $tableName.tableName) {\n"
    val slickColumns = slickColumnBuilder(table.columns())
    val provenShape = provenShapeBuilder
    val classTableTail = "\n}"
    classTableHead + slickColumns +provenShape+ classTableTail
  }
  def slickColumnBuilder(columns: util.List[Column]): String = {
    val columnStr = ListBuffer.empty[String]
    columns.forEach{
      column=>
        val columnType = wrapType(column)
        columnStr.addOne(s"\tdef ${convert(column.name())}: Rep[$columnType] = column[$columnType](\"${column.name()}\")")
    }
    columnStr.mkString("\n")
  }
  def provenShapeBuilder: String = {
    val keys = for (elem <- table.columns().asScala) yield s"\t\t${convert(elem.name())}"
    s"\n\tdef * = (\n" +
      keys.mkString(",\n")+
      s"\n\t) <> ((${table.id().toString}.apply _).tupled, ${table.id().toString}.unapply)"
  }
  def build: String =
    imports.mkString("\n") + "\n\n" + caseClassBuilder + "\n\n" + objectTableBuilder + "\n\n" + classTableBuilder
}


