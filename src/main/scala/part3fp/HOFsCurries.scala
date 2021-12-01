package part3fp

object HOFsCurries extends App {

  // Why don't we just define crazy complex functions like this?

  // This is a valid higher order function
  val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = null

  // Why should we like this?

  // function that applies a function n times over a value x
  // nTimes(f, n, x)
  // nTimes(f, 3, x) = f( f( f(x) ) )

  // This looks a whole lot like math because it is
  def nTimes(f: Int => Int, n: Int, x: Int): Int = {
    if (n <= 0) x
    else nTimes(f, n - 1, f(x))
  }

  val plusOne = (x: Int) => x + 1
  println(nTimes(plusOne, 10, 1))


  // What if we could make these functions parameterizable?
  // Well Scala gives us that via returning anonymous functions
  def nTimesBetter(f: Int => Int, n: Int): (Int => Int) = {
    if (n <= 0) (x: Int) => x
    else (x: Int) => nTimes(f, n, x)
  }

  println(nTimesBetter(plusOne, 10)(1))


  // So now lets talk curried functions, functions that return functions
  val superAdder: Int => Int => Int = (x: Int) => (y: Int) => x + y
  // Arrow is right associative so parenthesis implied by the compiler
//  val superAdder: Int => (Int => Int) = (x: Int) => (y: Int) => x + y

  // functions with multiple parameter lists
  // can act like curried functions without the syntax
  // more syntactic sugar
  def curriedFormatter(c: String)(x: Double): String = c.format(x)


  // Why is this important?
  // We just defined a function that returns functions (curried formatter) and that function generator
  // can be customized to produce other functions (standardFormat, preciseFormat)
  val standardFormat: (Double => String) = curriedFormatter("%4.2f")
  val preciseFormat: (Double => String) = curriedFormatter("%10.8f")

  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))

  // One limitation
  // You must specify the types to have curried functions working
  // This will break
  // val standardFormat = curriedFormatter("%4.2f")


  /**
   * 1. Expand MyList
   *    - foreach method A => Unit
   *    [1,2,3].foreach(x => println(x))
   *    - sort function ((A, A) => Int)  => MyList
   *    [1,2,3].sort((x, y) => y - x) => [3, 2, 1]
   *
   *    - zipWith (list, (A, A) => B) => MyList[B]
   *    [1,2,3].zipWith([4,5,6], x * y) => [1 * 4, 2 * 5, 3 * 6]
   *
   *    - fold(start)(function) => a value
   *    [1, 2, 3].fold(0)(x + y) = 6
   *
   *  2. toCurry(f: (int, Int) => Int) => (Int => Int => Int)
   *     fromCurry(f: (int => Int => Int)) => (Int, Int) => Int
   *
   *  3. compose(f, g) => x => f(g(x))
   *     andThen(f, g) => x => g(f(x))
   */
}
