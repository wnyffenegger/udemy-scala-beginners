package part1Basics

object Expressions extends App {

  val x = 1 + 2
  println(x)

  println(2 + 3 * 4)
  // + - * / ^ << >> >>>

  println(1 == x)

  println(!(1 == x))
  // ! && ||

  var aVariable = 2
  aVariable += 3

  // Expressions vs. Instructions example
  val aCondition = true
  val aConditionedValue = if(aCondition) 5 else 3
  println(aConditionedValue)
  println(if(aCondition) 5 else 3)

  // NEVER WRITE THIS AGAIN.
  var i = 0
  while (i < 10) {
    println(i)
    i += 1
  }

  // Unit == void
  // side effects
  var aWeirdValue = (aVariable = 3)
  println(aWeirdValue)

  // Code blocks
  // curly brace syntax
  // Value is the value of the last expression and type of result corresponds
  // Code blocks scope variables
  var aCodeBlock = {
    val y = 2
    val z = y + 1
    if (z > 2) "hello" else "goodbye"
  }

//  val a = z + 1


}
