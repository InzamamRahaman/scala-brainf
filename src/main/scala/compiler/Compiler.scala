package compiler
import ast._
import sys.process._
import java.io._

/**
 * @author inzamamrahaman
 */


object Compiler {
  
  private def write(path: String, txt: String): Unit = {
    val writer = new PrintWriter(new File(path))
    writer.write(txt)
    writer.close()
  }
  
  private def genSequence(arr: Seq[Node]) = 
    arr.map(transpile).mkString("\n")
    
  def compileProgramme(outputName: String, node: Node) = {
    write(outputName, transpile(node))
//    val command = s"""
//        ${transpile(node)} | gcc -x c -o ${outputName}
//      """
    //val exitcode = command.!!
    //exitcode
  }  
  
  def transpile(node: Node): String = node match {
    case Plus => "arr[idx]++;"
    case Minus => "arr[idx]--;"
    case Up => "idx++;"
    case Down => "idx--;"
    case Write => "printf(\"%c\", arr[idx]);"
    case Read => """
        scanf("%c", &ch);
        arr[idx] = ch;
      """  
    case Loop(commands) => s"while(arr[idx]) { ${genSequence(commands)}  }"
    case Programme(commands) => s"""
        #include <stdio.h>
        #include <stdlib.h>
        #define MAX_SIZE 1000
        
        int main()
        {
          char ch;
          int idx = 0;
          char arr[MAX_SIZE];
          ${genSequence(commands)}
          return 0;
        }
      """
  }
  
}