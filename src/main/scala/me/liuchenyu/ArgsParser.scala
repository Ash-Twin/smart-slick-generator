package me.liuchenyu

import scala.Function.tupled
import scala.annotation.tailrec


case class ArgsParser(args:Array[String]) {
  def apply: Map[String,String]= {
    val tuple2s = args.grouped(2).toList
    collect(tuple2s)
  }
  @tailrec
  final def collect(list:List[Array[String]], map:Map[String,String] = Map.empty): Map[String,String] ={
    list match {
      case Nil =>
        map
      case head::next =>
        collect(next,map.updated(head(0),head(1)))
    }
  }

}