//package idealized.scala
//
//
//abstract class Boolean {
//
//  def ifThenElse[T](t: => T, e: => T): T
//
//  def && (x: => Boolean): Boolean = ifThenElse(x, false)
//  def || (x: => Boolean): Boolean = ifThenElse(true, x)
//  def unary_! : Boolean            = ifThenElse(false, true)
//  def == (x: Boolean): Boolean    = ifThenElse(x, x.unary_!)
//  def != (x: Boolean): Boolean    = ifThenElse(x.unary_!, x)
//
//  def < (x: Boolean): Boolean     = ifThenElse(true, false)
//
//}
//
//object otrue extends Boolean {
//
//  def ifThenElse[T](t: => T, e: => T) = t
//}
//
//object ofalse extends Boolean {
//
//  def ifThenElse[T](t: => T, e: => T) = e
//}
//
//abstract class Nat {
//
//  def isZero: Boolean
//  def predecessor: Nat
//  def successor: Nat
//  def + (that: Nat): Nat
//  def - (that: Nat): Nat
//}
//
//abstract class Succ(n: Nat) extends Nat {
//
//}
//object Positive extends Nat {
//
//  override def isZero: Boolean = ???
//
//  override def predecessor: Nat = ???
//
//  override def successor: Nat = ???
//
//  override def +(that: Nat): Nat = ???
//
//  override def -(that: Nat): Nat = ???
//}
//object Zero extends Nat {
//
//  override def isZero: Boolean = otrue
//
//  override def predecessor: Nat = ???
//
//  override def successor: Nat = ???
//
//  override def +(that: Nat): Nat = ???
//
//  override def -(that: Nat): Nat = ???
//}
//
//object Main extends App {
//
//  println(false < true)
//}