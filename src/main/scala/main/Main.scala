package main

import parser._
import interpreter._
import scala.util.Success
import scala.util.Failure

/**
 * @author inzamamrahaman
 */
object Main {
  
  def isWhiteSpace(ch : Char) = 
    ch == ' ' || ch == '\t' || ch == '\n'
  
  def main(args : Array[String]) {
    if(args.length == 0) {
      println("needs file to interpret.....")
    } else {
      val filename = args(0)
      val file = scala.io.Source.fromFile(filename)
      try {
        val contents = file.mkString.filterNot(isWhiteSpace)
        val res = new BrainFParser(contents).InputLine.run()
        res match {
          case Success(tree) => Interpreter(tree)
          case Failure(err) => println(err)
        }
      } catch {
        case e : Exception => println("Error reading file ${filename}")
      } finally {
        file.close()
      }
    }
  }
  
  
}
  