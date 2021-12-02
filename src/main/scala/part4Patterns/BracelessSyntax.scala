package part4Patterns

import scala.language.postfixOps

object BracelessSyntax extends App {

  // Scala 3 functionality

  // One liner
  val anIfExpression = if (2 > 3) "bigger" else "smaller"

  // java style
  val anIfExpression_v2 = if (2 > 3) {
    "bigger" }
  else {
    "smaller"
  }

  // no parens
  val anIfExpression_v3 =
    if (2 > 3) "bigger"
    else "smaller"

  // Scala 3 Only

  // scala 3 then (a.k.a Python style
  // must be indented
  // everything under indentation is a code block
  val anIfExpression_v4 =
    if 2 > 3 then
      "bigger"
    else
      "smaller"

  val anIfExpression_v5 =
    if 2 > 3 then
      val result = "bigger"
      result
    else
      "smaller"

  println(anIfExpression_v5)

  val anIfExpression_v6 = if 2 > 3 then "bigger" else "smaller"


  // For Comprehensions

  val aForComprehension = for {
    n <- List(1, 2, 3)
    s <- List("black", "white")
  } yield s"$n$s"

  val aForComprehension_v2 = for
    n <- List(1, 2, 3)
    s <- List("black", "white")
  yield s"$n$s"


  // Pattern matching

  val meaningOfLife = 42
  val aPatternMatch = meaningOfLife match {
    case 1 => "the one"
    case _ => "something else"
  }

  val aPatternMatch_v2 = meaningOfLife match
    case 1 => "the one"
    case _ => "something else"


    // Methods without braces
    def computeMeaningOfLife(arg: Int): Int =
      val partialResult = 40

      partialResult + 2

    println(computeMeaningOfLife(1))


    // Classes, traits, enums, objects and data types with significant indentation

    // Colon says use indentation
    class Animal:
      def eat(): Unit =
        println("I'm eating")

      def grow(): Unit =
        println("I'm growing")


    // End tokens
    class Animal2:
      def eat: Unit =
        println("I'm eating")
      end eat

      def grow: Unit =
        println("I'm growing")
      end grow

    end Animal2

    println(new Animal2().eat)


    // Anonymous classes
    val aSpecialAnimal = new Animal:
        override def eat(): Unit = println("I'm special")
}
