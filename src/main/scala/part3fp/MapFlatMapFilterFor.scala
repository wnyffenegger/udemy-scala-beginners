package part3fp

object MapFlatMapFilterFor extends App {

  // Official versions  of these functions

  val list = List(1, 2, 3)
  println(list.head)
  println(list.tail)

  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))

  println(list.filter(_ % 2 == 0))

  val toPair = (x: Int) => List(x, x + 1)
  println(list.flatMap(toPair))

  /**
   * Print out all combinations between two lists
   */

  // The functional version of iterating
  val numbers = List(1, 2, 3, 4)
  val chars = List('a', 'b', 'c', 'd')
  println(numbers.flatMap(n => chars.map(c => "" + c + n)))

  // foreach
  list.foreach(println)

  val colors = List("black", "white")

  // for-comprehensions
  // are syntactic sugar for multiple maps
  val forCombinations = for {
    n <- numbers
    c <- chars
    color <- colors
  } yield "" + c + n + "-" + colors
  println(forCombinations)

  // To filter out specific sections
  val forCombinationsFilter = for {
    n <- numbers if n % 2 == 0
    c <- chars
    color <- colors
  } yield "" + c + n + "-" + colors
  println(forCombinationsFilter)

  // If you want side effects it handles that too
  for {
    n <- numbers
  } println(n)

  /**
   * 1. Does MyList support for comprehensions?
   * 2. Implement of a small collection of at most one element - Maybe[+T]
   *    - map, flatMap, filter
   */

  abstract class Maybe[+T] {

    def map[B](f: T => B): Maybe[B]
    def flatMap[B](f: T => Maybe[B]): Maybe[B]
    def filter(p: T => Boolean): Maybe[T]
  }

  case object MaybeNot extends Maybe[Nothing] {
    def map[B](f: Nothing => B): Maybe[B] = MaybeNot
    def flatMap[B](f: Nothing => Maybe[B]): Maybe[B] = MaybeNot
    def filter(p: Nothing => Boolean) = MaybeNot
  }

  case class Just[+T](value: T) extends Maybe[T] {
    override def map[B](f: T => B): Maybe[B] = Just(f(value))

    override def flatMap[B](f: T => Maybe[B]): Maybe[B] = f(value)

    override def filter(p: T => Boolean): Maybe[T] =
      if (p(value)) this
      else MaybeNot
  }

  val just3 = Just(3)
  println(just3)
  println(just3.map(_ * 2))
  println(just3.flatMap(x => Just(x % 2 == 0)))
  println(just3.filter(_ % 2 == 0))
}
