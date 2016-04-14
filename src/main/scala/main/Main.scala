package main

import parser._
import interpreter._
import scala.util.Success
import scala.util.Failure

/**
 * @author inzamamrahaman
 */

import ast._
import compiler._
import interpreter._

object Main {
  
  def isWhiteSpace(ch : Char) = 
    ch == ' ' || ch == '\t' || ch == '\n'
    
  def handleSourceFile(args: Array[String], tree: Node, filename: String) = {
    println("file: " + filename)
    println(args.mkString(" , "))
    val flag = if (args.length > 1) args(1).trim() else "--i"
    println(flag)  
    if(flag == "--c") {
      println("Compiling programme")
      Compiler.compileProgramme(filename.replace(".bf", ".c"), tree)
    } else {
      Interpreter(tree)
    }
  }
  
  
  def main(args : Array[String]) {
    if(args.length == 0) {
      println("needs file to interpret or compile.....")
    } else {
      val filename = args(0)
      val file = scala.io.Source.fromFile(filename)
      try {
        val contents = file.mkString.filterNot(isWhiteSpace)
        val res = new BrainFParser(contents).InputLine.run()
        res match {
          case Success(tree) => handleSourceFile(args, tree, filename)
          case Failure(err) => println(err)
        }
      } catch {
        case e : Exception => println(s"Error reading file ${filename}")
      } finally {
        file.close()
      }
    }
  }
 
  
  
}
  