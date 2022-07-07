name := "smart-slick"

version := "0.1"

scalaVersion := "2.13.6"

val proj = (project in file("."))
  .enablePlugins(JavaAppPackaging)
  .settings(
    // https://mvnrepository.com/artifact/io.debezium/debezium-connector-mysql
    libraryDependencies += "io.debezium" % "debezium-connector-mysql" % "1.9.4.Final"
  )
