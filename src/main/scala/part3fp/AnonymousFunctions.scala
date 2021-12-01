package part3fp

object AnonymousFunctions extends App {

  // Java like syntax
  val doubler = new Function1[Int, Int] {
    override def apply(x: Int) = x * 2
  }

  // Scala syntax
  // Anonymous function also called Lambda
  val doublerScala = (x: Int) => x * 2

  // Infer the types to make it short
  val doublerScalaShort: Int => Int = (x) => x * 2

  // multiple parameters in a lambda?
  val adder = (a: Int, b: Int) => a + b

  val adderTyped: (Int, Int) => Int = (a: Int, b: Int) => a + b

  // no parameters
  val justDoSomething: () => Int = () => 3
  // memory location of function
  println(justDoSomething)

  // function execution
  println(justDoSomething())

  // other syntactical style
  val stringToInt = { (str: String) =>
    str.toInt
  }

  // more syntactic sugar
  val niceIncrementer: Int => Int = (x: Int) => x + 1
  val niceIncShort: Int => Int = _ + 1

  // Same as (a, b) => a + b
  // but requires that types are absolutely clear
  // second one won't work
  val niceAdder: (Int, Int) => Int = _ + _
//  val niceAdder = _ + _

  /**
   * 1. MyList: replace all FunctionX calls with lambdas
   * 2. Rewrite the "special" adder as an anonymous function
   */
}
