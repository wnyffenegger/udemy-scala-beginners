package part4Patterns

object PatternsEverywhere extends App {


  // This is pattern matching
  try {

  } catch {
    case e: RuntimeException => "runtime"
    case npe: NullPointerException => "npe"
    case _ => "something else"
  }


  // All the generators that you have are based on pattern matching
  val list = List(1,2,3,4)
  val evenOnes = for {
    // Pattern matching using if guards
    x <- list if x % 2 == 0
  } yield 10 * x

  val tuples = List((1,2), (3, 4))
  val filterTuples = for {
    (first, second) <- tuples
  } yield first * second

  // Case classes, :: operators also work with generators


  // Name binding property of pattern matching
  val tuple = (1, 2, 3)
  val (a, b, c) = tuple

  val head :: tail = list
  println(head)
  println(tail)


  // Partial function
  val mappedList = list.map {
    case v if v % 2 == 0 => s"$v is even"
    case 1 => "the one"
    case _ => "something else"
  }

  // Syntactically same as above
  val mappedList2 = list.map(x => x match {
    case v if v % 2 == 0 => s"$v is even"
    case 1 => "the one"
    case _ => "something else"
  })


}
