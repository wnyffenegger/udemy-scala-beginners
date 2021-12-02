package part4Patterns

import scala.util.Random

object PatternMatching extends App {

  val random = new Random()

  val x = random.nextInt(10)
  val description = x match {
    case 1 => "the One"
    case 2 => "double or nothing"
    case 3 => "third"
    case _ => "meh"
  }

  println(x)
  println(description)

  /**
   * 1. Cases are matched in order
   * 2. If no cases match you get a match error
   * 3. type of the expression is the unified type of all the types in all the cases (lowest common denominator)
   * 4. works well with cases classes
   * 5. compiler will warn you if you use a sealed class
   */

  // 1. Decompose values
  // with filtering
  case class Person(name: String, age: Int)
  val bob = Person("Bob", 20)

  // Cases are matched in order
  val greeting = bob match {
    case Person(n, a) if a < 21 => s"Hi, my name is $n and I can't drink in the US"
    case Person(n, a) => s"Hi, my name is $n and I am $a years old"
    case _ => "I don't know who I am"
  }
  println(greeting)

//  val greeting2 = bob match {
//    case Person(n, a) if a > 21 => s"Hi, my name is $n and I can't drink in the US"
////    case _ => "I don't know who I am"
//  }
//  println(greeting2)

  sealed class Animal
  case class Dog(breed: String) extends Animal
  case class Parrot(greeting: String) extends Animal

  val animal: Animal = Dog("Terra Nova")
  animal match {
    case Dog(someBreed) => println(s"Matched a dog of the $someBreed breed")
  }


  /**
   * Exercises
   * Given an expression return human readable form of it
   */

  trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Prod(e1: Expr, e2: Expr) extends Expr

  def readable(expr: Expr): String = {
    expr match {
      case Number(x) => s"$x"
      case Sum(e1, e2) => s"(${readable(e1)} + ${readable(e2)})"
      case Prod(e1, e2) => s"(${readable(e1)} * ${readable(e2)})"
    }
  }

  println(readable(Prod(Sum(Number(10), Number(2)), Prod(Number(5), Number(3)))))

}
