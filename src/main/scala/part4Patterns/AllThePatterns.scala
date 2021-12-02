package part4Patterns

import exercises.{Cons, Empty, MyList}

object AllThePatterns extends App {

//  // 1 - constants
//  val x: Any = "Scala"
//  val constants = x match {
//    case 1 => "a number"
//    case "Scala" => "THE scala"
//    case true => "The Truth"
//    // Even singleton objects qualify as constants
//    case AllThePatterns => "A singleton object"
//  }
//
//  // 2 - match anything
//
//  // wild card
//  val matchAnything = x match {
//    case _ =>
//  }
//
//  // variable
//  val matchAVariable = x match {
//    case something => s"I've found $something"
//  }
//  println(matchAnything)
//  println(matchAVariable)
//
//  // 3 - tuples
//  val aTuple = (1, 2)
//  val matchATuple = aTuple match {
//    case (1, 1) =>
//    case(something, 2) => s"I've found $something"
//  }
//
//  // Pattern matches can be nested
//  val nestedTuple = (1, (2, 3))
//  val matchANestedTuple = nestedTuple match {
//    case (_, (2, v)) =>
//  }
//
//  // 4 - case classes - constructor pattern
//  // can be nested as well
//  val aList: MyList[Int] = Cons(1, Cons(2, Empty))
//  val matchAList = aList match {
//    case Empty =>
//    case Cons(head, Cons(subhead, subtail)) =>
//  }
//
//  // 5 - list patterns
//  val aStandardList = List(1, 2, 3, 42)
//  val standardListMatching = aStandardList match {
//    case List(1, _, _, _) => // extractor - advanced
//    case List(1, _*) => // list of arbitrary length
//    case 1 :: List(_) => // infix pattern
//    case List(1, 2, 3) :+ 42 => // infix pattern
//  }
//
//  // 6 - type specifiers
//  val unknown: Any = 2
//  val unkownMatch = unknown match {
//    case list: List[Int] => // explicit type specifier
//    case _ =>
//  }
//
//  // 7 - name binding
//  val nameBindingMatch = aList match {
//    case nonEmpty @ Cons(_, _) => // makes you use the name later
//    case Cons(1, rest @ Cons(2, _)) => // name binding works with nested patterns
//  }
//
//  // 8 - multi-patterns
//  val multipattern = aList match {
//    case Empty | Cons(0, _) => // compound pattern
//  }
//
//  // 9 - if guards
//  val secondElementSpecial = aList match {
//    case Cons(_, Cons(specialElement, _)) if specialElement % 2 == 0 =>
//  }

  /**
   * Question
   */

  // Matches a list of strings instead
  // JVM tricks you because generics limit it by type erasure
  val numbers = List(1, 2, 3)
  val numbersMatch = numbers match {
    case listOfString: List[String] => "a list of strings"
    case listOfNumbers: List[Int] => "a list of numbers"
    case _ => ""
  }
  println(numbersMatch)

}
