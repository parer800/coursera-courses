package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
    def pascal(c: Int, r: Int): Int = {

      def isEdge(): Boolean =
        c == 0 || c == r

      if(isEdge())
        1
      else
        pascal(c-1, r-1) + pascal(c, r-1)
    }
  
  /**
   * Exercise 2
   */
    def balance(chars: List[Char]): Boolean = {

      def loop(balance: Int, chars: List[Char]): Boolean =

        if(chars.isEmpty)
          balance == 0

        else {
          if (balance < 0)
            return false

          chars.head match {
            case '(' => loop(balance + 1, chars.tail) // matched opening parenthesis, add positive balance
            case ')' => loop(balance - 1, chars.tail) // matched opening parenthesis, add negative balance
            case _ => loop(balance, chars.tail) // no parenthesis, no need ta adjust balance
          }
        }

      loop(0, chars)
    }
  
  /**
   * Exercise 3
   */
    def countChange(money: Int, coins: List[Int]): Int = {

      if(money == 0)
        1
      else if(coins.isEmpty || money < 0)
        0
      else countChange(money, coins.tail) + countChange(money - coins.head, coins)
    }
  }
