package examples

/**
  * TODO
  */
object SetsExample {

  def main(args: Array[String]) = println("hello world")
  val set1 = new NonEmpty(3, new Empty, new Empty);
  val set2 = set1 incl 4
  val set3 = set2 incl 2
  val set4 = set1 incl 7
  println(set3 union set4)
}


abstract class IntSet {
  def incl(x: Int): IntSet
  def contains(x: Int): Boolean

  def union(other: IntSet): IntSet
}

class Empty extends IntSet {

  def contains(x: Int): Boolean = false
  def incl(x: Int): IntSet = new NonEmpty(x, new Empty, new Empty)

  override def toString = "."

  override def union(other: IntSet): IntSet = other
}

class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {

  def contains(x: Int): Boolean =
    if (x < elem) left contains x
    else if (x > elem) right contains x
    else true

  def incl(x: Int): IntSet =
    if (x < elem) new NonEmpty(elem, left incl x, right)
    else if (x > elem) new NonEmpty(elem, left, right incl x)
    else this

  override def toString = "{" + left + elem + right + "}"

  override def union(other: IntSet): IntSet =
    ((left union right) union other) incl elem
}