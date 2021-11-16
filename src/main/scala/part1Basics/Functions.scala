package part1Basics

object Functions extends App {

  // Each parameter must have a name and type
  // Must have a return type
  // Can be wrapped in a code block
  def aFunction(a: String, b: Int): String =
    a + " " + b

  println(aFunction("hello", 3))

  def aParameterlessFunction(): Int = 42
  println(aParameterlessFunction())

  // Not syntactically sound apparently even though video says it is
  //  println(aParameterlessFunction)

  def aRepeatedFunction(aString: String, n: Int): String = {
    if (n == 1) aString
    else aString + aRepeatedFunction(aString, n - 1)
  }

  println(aRepeatedFunction("hello", 3))

  def aFunctionWithSideEffects(aString: String): Unit = println(aString)

  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int): Int = a + b

    aSmallerFunction(n, n - 1)
  }

  println(aBigFunction(10))


  /*
    1. A greeting function (name, age) => "Hi, my name is _ and I am _ years old
    2. A factorial function
    3. A fibonacci function
    4. Tests if a number is prime
  */

  def greeting(name: String, age: Int): Unit = {
    println("Hi, my name is " + name + " and I am " + age + " years old")
  }
  greeting("Bob", 10)

  def factorial(n: Int): Int = {
    if (n == 1) 1
    else n * factorial(n - 1)
  }
  println(factorial(4))

  def fibonacci(n: Int): Int = {
    if (n == 1 || n == 2) 1
    else fibonacci(n - 1) + fibonacci(n - 2)
  }
  println(fibonacci(5))
}
