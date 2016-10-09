package funsets

import org.scalatest.FunSuite


import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  // test("string take") {
  //   val message = "hello, world"
  //   assert(message.take(5) == "hello")
  // }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  // test("adding ints") {
  //   assert(1 + 2 === 3)
  // }


  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val range1: Set = x => x > 0 && x < 10
    val range2: Set = x => x > 5 && x < 15
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements of each set") {
    new TestSets {
      val union1 = union(s1, s2)
      val union2 = union(range1, range2)
      assert(contains(union1, 1), "Union 1")
      assert(contains(union1, 2), "Union 2")
      assert(!contains(union1, 3), "Union 3")

      assert(contains(union2, 1), "1 is within the union")
      assert(contains(union2, 3), "3 is within the union")
      assert(contains(union2, 5), "5 is within the union")
      assert(contains(union2, 9), "9 is within the union")
      assert(contains(union2, 10), "10 is within the union")
      assert(contains(union2, 14), "14 is within the union")
      assert(!contains(union2, 0), "0 is outside the union")
      assert(!contains(union2, 15), "15 is outside the union")
    }
  }
  test("intersection contains all elements shared by each set") {
    new TestSets {
      val intersection1 = intersect(s1, s1)
      val intersection2 = intersect(range1, range2)
      assert(contains(intersection1, 1), "Intersection 1")
      assert(!contains(intersection1, 2), "Intersection 2")

      assert(contains(intersection2, 6), "6 is within range1 and range2")
      assert(!contains(intersection2, 5), "5 is within range1 but not range2")
      assert(!contains(intersection2, 10), "10 is within range2 but not range1")


    }
  }
  test("filter the subset of one set according to a set condition") {
    new TestSets {
      val condition: Int => Boolean = x => x <= 5
      val filteredSubset = filter(range1, condition)

      assert(contains(filteredSubset, 1), "1 is within range1 and fulfills filter condition")
      assert(contains(filteredSubset, 5), "5 is within range1 and fulfills filter condition")
      assert(!contains(filteredSubset, 6), "6 is within range1 but doesn't fulfill filter condition")
      assert(!contains(filteredSubset, 0), "0 fulfills filter condition but is not within range1")
    }
  }
  test("test if all values in a set condition matches a condition") {
    new TestSets {
      val positiveCondition: Int => Boolean = x => x >= 0
      val forAllResult = forall(range1, positiveCondition)

      val evenCondition: Int => Boolean = (x => x % 2 == 0)
      val largeRange: Set = x => -bound < x && x < bound
      val largeEvenRange: Set = x => -bound < x && x < bound && evenCondition(x)


      assert(forall(largeEvenRange, evenCondition), "even numbers are matched")
      assert(!forall(largeRange, evenCondition), "should only match even numbers")
      assert(forAllResult, "1 is within range1 and fulfills filter condition")
    }
  }
  test("test if values matching a condition exists in a set") {

    assert(!exists(singletonSet(-1001), x => x < 0), "value doesn't exist no match")
    assert(exists(singletonSet(-100), x => x < 0), "value matching condition")
    assert(!exists(singletonSet(-100), x => x > 0), "value not matching condition")

  }

  test("test map of set") {
    new TestSets {

      val expectedResultString = "{2,4,6,8,10,12,14,16,18}"
      val mappedResult = map(range1, x => x * 2)

      val actualResultString = FunSets.toString(mappedResult)
      assert(expectedResultString == actualResultString, "map should double each element")

    }
  }

}
