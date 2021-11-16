package part1Basics

object ValuesVariablesTypes extends App {

//  val x: Int = 42
  val x = 42
  println(x)

// vals are immutable
//  x = 2

  val astring: String = "hello"
  val anotherstring = "goodbye"

  val aBoolean: Boolean = false
  val aChar: Char = 'a'
  val anInt: Int = x
  
// Shorts can't overflow
//  val aShort: Short = 444444444
  val aShort: Short = 443

  val aLong: Long = 555555555555555555L
  val aFloat: Float = 2.0f
  val aDouble: Double = 3.14
  
  var aVariable: Int = 4
  aVariable = 5
// Does type checking
//  aVariable = "oops"
  
}
