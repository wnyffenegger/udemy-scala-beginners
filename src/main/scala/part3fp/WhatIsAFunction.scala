package part3fp

object WhatIsAFunction extends App {


  // work with functions like we work with plain values
  // problem: oop (everything is a class)
  // Java only way is to create a class with a function and then
  // instantiate the class and run the desired function

  // Ex.
  trait Action[A, B] {
    def execute(element: A): B
  }

  // Scala way to make it look functional
  trait MyFunction[A, B] {
    def apply(element: A): B
  }

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  // syntactic sugar makes this look functional
  println(doubler(2))

  /**
   * Scala supports Function1 - Function22 which are predefined traits for one to 22 parameter functions
   */

  val stringToInt = new Function1[String, Int] {
    override def apply(string: String): Int = string.toInt
  }



  println(stringToInt("3") + 4)

  val adder: ((Int, Int) => Int) = new Function2[Int, Int, Int] {
    override def apply(v1: Int, v2: Int): Int = v1 + v2
  }

  /**
   * 1. A fucntion which takes 2 strings and concatenates them
   * 2. Transform the MyPredicate and MyTransformer into function types
   * 3. Define a function which takes an in and returns another function which takes an int and returns an int
   *    - what's the type of this function
   *    - how to do it
   */
  
  // 1.
}
