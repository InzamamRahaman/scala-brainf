package semantics

/**
  * Created by inzamamrahaman on 17/01/2016.
  */

import scala.collection.mutable.ArrayBuffer

class Environment(array : ArrayBuffer[Char], loc : Int) {


  private def adjustPointer(n : Int) = {
    val arraySize = array.size
    if(n == arraySize) {
      new Environment(array :+ 0.toChar, n)
    } else {
      new Environment(array, n)
    }
  }

  private def operateOnContents(fun : Char => Char) = {
    array(loc) = fun(array(loc))
    new Environment(array, loc)
  }


  def incrementPointer = adjustPointer(loc + 1)
  def decrementPointer = adjustPointer(loc - 1)
  def incrementContents = operateOnContents((x : Char) => (x.toInt + 1).toChar)
  def decrementContents = operateOnContents((x : Char) => (x.toInt - 1).toChar)
  def insertAt(ch : Char) = operateOnContents(_ => ch)
  def getContents = array(loc)
  def terminateLoop = array(loc).toInt == 0
  
  override def toString = 
    array.map(_.toInt).mkString(" ")


}


object Environment {
  val defaultSize = 1000
  def apply() = {
    val buffer : ArrayBuffer[Char] = ArrayBuffer(Array.fill[Char](defaultSize)(0) : _*)
    new Environment(buffer, 0)
  }
}