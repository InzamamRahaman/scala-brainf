package interpreter

import ast._
import semantics.Environment
import scala.io.StdIn

/**
  * Created by inzamamrahaman on 17/01/2016.
  */
object Interpreter {
 
  /*
   * Executes the commands, as represented by nodes, on the "tape"
   */
  private def handleNode(acc : Environment, node : Node) : Environment = node match {
    case Plus => acc.incrementContents
    case Minus => acc.decrementContents
    case Up => acc.incrementPointer
    case Down => acc.decrementPointer
    case Write => {
      println(acc.getContents)
      acc
    }
    case Read => {
      println("Waiting for Character")
      val ch : Char = StdIn.readChar()
      acc.insertAt(ch)
    }
    case Loop(commands) => {
      var temp = acc
      while(!temp.terminateLoop) {
        temp = commands.foldLeft(temp)(handleNode)
      }
      temp
    }
    case Programme(commands) => commands.foldLeft(acc)(handleNode)
  }

  def apply(commands : Node*) : Environment = {
    val env = commands.foldLeft(Environment())(handleNode)
    env
  }

}
