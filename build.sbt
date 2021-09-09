name := "smart-slick"

version := "0.1"

scalaVersion := "2.13.6"

val proj = (project in file("."))
  .enablePlugins(JavaAppPackaging)
  .settings(
    libraryDependencies += "io.debezium" % "debezium-connector-mysql" % "1.6.2.Final"
  )
