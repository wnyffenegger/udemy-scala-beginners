package part1Basics

import scala.annotation.tailrec

object Recursion extends App {

  // JVM has to use a call stack to run a recursive function
  // normal stack frame, blah, blah, blah
  // Recursive depth can cause stack overflows
//  @tailrec will fail because funtion is not tail recursive
  def factorial(n: Int): Int = {
    if (n == 1) 1
    else {
      println("Computing factorial of " + n)
      val result = n * factorial(n - 1)
      println("Computed factorial of " + n)

      result
    }
  }

// will cause stack frame crash
//  println(factorial(5000))

  // Switch to bigint to handle overflow
  // Why does this work even though on the surfact it is still recursive?
  // Answer: if the last expression is the recursive call, then it re-uses the current stack frame
  // This is called tail recursion
  def factorial2(n: BigInt): BigInt = {

    // Check whether function is tail recursive
    @tailrec
    def fHelper(x: BigInt, accumulator: BigInt): BigInt = {
      if (x <= 1) accumulator
      else fHelper(x - 1, x * accumulator)
    }

    fHelper(n, 1)
  }
  println(factorial2(4))
  println(factorial2(5000))
  println(factorial2(20000))


  // Tail recursion exercises
  // 1. Concatenate a string n times
  // 2. IsPrime function tail recursive
  // 3. Fibonacci function tail recursive

  def concat(word: String, n: Int): String = {
    @tailrec
    def cHelper(word: String, n: Int, accumulator: String): String = {
      if (n <= 1) accumulator
      else cHelper(word, n - 1, accumulator + word)
   }

    cHelper(word, n, word)
  }
  println(concat("a ", 10))

  def fibonacci(n: Int): BigInt = {
    @tailrec
    def fHelper(m: Int, minusOne: BigInt, minusTwo: BigInt): BigInt = {
        if (m == n) minusOne + minusTwo
        else fHelper(m + 1, minusOne + minusTwo, minusOne)
    }

    if (n <= 2) 1
    else fHelper(3, 1, 1)
  }
  println(fibonacci(5))
}
