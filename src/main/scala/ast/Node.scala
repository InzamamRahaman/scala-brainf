package ast

/*
 * These are nodes for the AST tree
 */

sealed abstract class Node

case object Plus extends Node
case object Minus extends Node
case object Up extends Node
case object Down extends Node
case object Write extends Node
case object Read extends Node
case class Loop(commands : Seq[Node]) extends Node
case class Programme(commands : Seq[Node]) extends Node

