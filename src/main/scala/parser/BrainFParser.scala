package parser


import org.parboiled2._
import ast._

/**
  * Created by inzamamrahaman on 17/01/2016.
  */
class BrainFParser(val input : ParserInput) extends Parser {


  def InputLine : Rule1[Node] = rule {
    programme ~ EOI
  }
  
  def programme : Rule1[Node] = rule {
    (oneOrMore(node))~> ((xs : Seq[Node]) => Programme(xs))
  }
    

  def node : Rule1[Node] = rule(loop | nonLoop) 
  def blnk : Rule0 = rule(zeroOrMore(anyOf(" \n\t")))

  def plus : Rule1[Node] = rule {
    capture('+') ~> ((_ : String) => Plus)
  }

  def minus : Rule1[Node] = rule {
    capture('-') ~> ((_ : String) => Minus)
  }

  def up : Rule1[Node] = rule {
    capture('>') ~> ((_ : String) => Up)
  }

  def down : Rule1[Node] = rule {
    capture("<") ~> ((_ : String) => Down)
  }

  def read : Rule1[Node] = rule {
    capture(",") ~> ((_ : String) => Read)
  }

  def write : Rule1[Node] = rule {
    capture(".") ~> ((_ : String) => Write)
  }

  def nonLoop : Rule1[Node] = rule {
    plus | minus | up | down | read | write
  }

  def loop : Rule1[Node] = rule {
    "[" ~ oneOrMore(node) ~ "]" ~>
      ((xs : Seq[Node]) => Loop(xs))
  }

}
