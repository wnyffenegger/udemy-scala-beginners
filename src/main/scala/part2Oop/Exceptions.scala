package part2Oop

object Exceptions extends App {

//  val x: String = null
//  println(x.length)

  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("Ouch")
    else 42

  try {
    getInt(true)
  } catch {
    case e: RuntimeException => println(s"${e} failed")
  } finally {
    println("finally")
  }

  // try/catch/finally is an expression
  // Type is determined by reading the types returned from the catch and from the try and picks the parent
  // type

  // Use finally only for side effects

  val potentialFail: AnyVal =   try {
    getInt(true)
  } catch {
    case e: RuntimeException => println(s"${e} failed")
  } finally {
    println("finally")
  }

  // Defining your own exceptions

  /**
   * 1. Crash your program with an OutOfMemoryError
   * 2. Crash with SOError
   * 3. PocketCalculator
   *    - adds two numbers
   *    - subtract
   *    - multiply
   *    - divide
   *    Throw OverflowException if add(x,y) exceeds Int.MAX_VALUE
   *    Throw UnderflowException if subtract(x,y) exceeds Int.MIN_VALUE
   *    Throw MathCalculationException for division by 0
   */

  class OverflowException extends Exception
  class UnderflowException extends Exception
  class MathCalculationException(message: String) extends Exception(message)

  class PocketCalculator {
    def add(x: Int, y: Int): Int = {
      val result = x + y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result > 0) throw new UnderflowException
      else result
    }

    def subtract(x: Int, y: Int): Int = {
      val result = x - y
      if (x > 0 && y < 0 && result < 0) throw new OverflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def multiply(x: Int, y: Int): Int = {
      val result = x - y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result < 0) throw new OverflowException
      else if (x > 0 && y < 0 && result > 0) throw new UnderflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def divide(x: Int, y: Int): Int = {
      if (y == 0) throw new MathCalculationException("divide by zero")
      else x / y
    }
  }

// OutOfMemory
//  val array:Array[Int] = Array.ofDim(Int.MaxValue)

// StackOverflow
//  def infinite: Int = 1 + infinite
//  val noLimit = infinite

  try {
    println(new PocketCalculator().add(Int.MaxValue, 1))
  } catch {
    case over: OverflowException => println("overflowed addition")
  }

  try {
    println(new PocketCalculator().subtract(Int.MinValue, 1))
  } catch {
    case under: UnderflowException => println("underflowed subtraction")
  }

  try {
    println(new PocketCalculator().divide(10, 0))
  } catch {
    case zero: MathCalculationException => println(zero)
  }
}
