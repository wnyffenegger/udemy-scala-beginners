package exercises

/**
 * 1. A fucntion which takes 2 strings and concatenates them
 * 2. Transform the MyPredicate and MyTransformer into function types
 * 3. Define a function which takes an in and returns another function which takes an int and returns an int
 *    - what's the type of this function
 *    - how to do it
 */

object WhatIsAFunction extends App {


  // 2. Get rid of these entirely and replace with Function1
//  trait MyPredicate[-T] {
//    override def test(element: T): Boolean
//  }
//
//  trait MyTransformer[-A,B] {
//    override def transform(element: A): B
//  }

  abstract class MyList[+A] {

    def head: A
    def tail: MyList[A]
    def isEmpty: Boolean
    def add[B >: A](element: B): MyList[B]
    def printElements: String
    override def toString: String = printElements

    def map[B](transformer: A => B): MyList[B]
    def filter(predicate: A => Boolean): MyList[A]
    def flatMap[B](transformer: A => MyList[B]): MyList[B]
    def ++[B >: A](list: MyList[B]): MyList[B]
  }

  case object Empty extends MyList[Nothing] {
    def head: Nothing = throw new NoSuchElementException

    override def tail: MyList[Nothing] = throw new NoSuchElementException

    override def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)

    override def isEmpty: Boolean = true

    override def printElements: String = ""

    def map[B](transformer: Nothing => B): MyList[B] = Empty

    def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty

    def flatMap[B](transformer: Nothing => MyList[B]) = Empty

    def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
  }

  case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
    def head: A = h
    def tail: MyList[A] = t
    def isEmpty: Boolean = false
    def add[B >: A](element: B): MyList[B] = new Cons(element, this)

    def printElements: String = {
      if (t.isEmpty) s"$h"
      // This should be tail recursion
      else s"$h ${t.printElements}"
    }

    def map[B](transformer: A => B): MyList[B] = {
      new Cons(transformer(h), tail.map(transformer))
    }

    def filter(predicate: A => Boolean): MyList[A] = {
      if (predicate(h))
        new Cons(h, t.filter(predicate))
      else
        t.filter(predicate)
    }

    def flatMap[B](transformer: A => MyList[B]): MyList[B] = {
      transformer(h) ++ t.flatMap(transformer)
    }

    def ++[B >: A](list: MyList[B]): MyList[B] = {
      new Cons(h, t ++ list)
    }
  }

  println("Generics Exercises")
  var listOfInts: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  var listOfVals: MyList[AnyVal] = new Cons(1, new Cons(true, new Cons(4.0, Empty)))
  var listOfStrings: MyList[String] = Empty

  println(listOfInts)
  println(listOfVals)
  println(listOfStrings)

  println("OOPList Exercises")

  println(listOfInts.map(new Function1[Int, Int] {
      def apply(element: Int): Int = 2 * element
  }))

  println(listOfInts.filter(new Function1[Int, Boolean] {
    override def apply(element: Int): Boolean = element % 2 == 0
  }))

  println(listOfInts.flatMap(new Function1[Int, MyList[Int]] {
    override def apply(element: Int): MyList[Int] = new Cons(element, new Cons(element, Empty))
  }))


  println("Case class exercises")

  var secondListOfInts: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  println(listOfInts)
  println(listOfInts == secondListOfInts)


  println("Functional Exercises")

  // 1
  val concat = new Function2[String, String, String] {
    override def apply(v1: String, v2: String): String = v1 + v2
  }

  println(concat("AB", "CD"))

  val multiple: ((Int) => Function1[Int, Int]) = new Function1[Int, Function1[Int, Int]] {
    override def apply(v1: Int): Int => Int = new Function1[Int, Int] {
      override def apply(v2: Int): Int = v1 * v2
    }
  }

  println(multiple(10)(2))
}
