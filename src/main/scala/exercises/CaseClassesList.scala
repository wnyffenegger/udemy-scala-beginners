package exercises

/**
 * 1. Generic trait MyPredicate[-T] (with a little method test(T) => Boolean
 * 2. Generic trait MyTransformer[-A,B] with a method transform(A) => B
 * 3. MyList:
 *    - map(transformer) => MyList of transformer type
 *    - filter(predicate) => MyList filtered
 *    - flatMap(transformer from A to MyList[B]) => MyList[B]
 *
 *    class EvenPredicate extends MyPredicate[Int]
 *    class StringToIntTransformer extends MyTransformer[String, Int]
 *
 *    [1,2,3].map(n * 2) = [2, 4, 6]
 *    [1, 2, 3, 4].filter(n % 2) = [2,4]
 *    [1, 2, 3].flatMap(n => [n, n + 1]) => [1, 2, 2, 3, 3, 4]
 */

object CaseClassesList extends App {

  trait MyPredicate[-T] {
    def test(element: T): Boolean
  }

  trait MyTransformer[-A,B] {
    def transform(element: A): B
  }

  abstract class MyList[+A] {

    def head: A
    def tail: MyList[A]
    def isEmpty: Boolean
    def add[B >: A](element: B): MyList[B]
    def printElements: String
    override def toString: String = printElements

    def map[B](transformer: MyTransformer[A, B]): MyList[B]
    def filter(predicate: MyPredicate[A]): MyList[A]
    def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]
    def ++[B >: A](list: MyList[B]): MyList[B]
  }

  case object Empty extends MyList[Nothing] {
    def head: Nothing = throw new NoSuchElementException

    override def tail: MyList[Nothing] = throw new NoSuchElementException

    override def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)

    override def isEmpty: Boolean = true

    override def printElements: String = ""

    def map[B](transformer: MyTransformer[Nothing, B]): MyList[B] = Empty

    def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = Empty

    def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]) = Empty

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

    def map[B](transformer: MyTransformer[A, B]): MyList[B] = {
      new Cons(transformer.transform(h), tail.map(transformer))
    }

    def filter(predicate: MyPredicate[A]): MyList[A] = {
      if (predicate.test(h))
        new Cons(h, t.filter(predicate))
      else
        t.filter(predicate)
    }

    def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] = {
      transformer.transform(h) ++ t.flatMap(transformer)
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

  println(listOfInts.map(new MyTransformer[Int, Int] {
      def transform(element: Int): Int = 2 * element
  }))

  println(listOfInts.filter(new MyPredicate[Int] {
    override def test(element: Int): Boolean = element % 2 == 0
  }))

  println(listOfInts.flatMap(new MyTransformer[Int, MyList[Int]] {
    override def transform(element: Int): MyList[Int] = new Cons(element, new Cons(element, Empty))
  }))


  println("Case class exercises")

  var secondListOfInts: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  println(listOfInts)
  println(listOfInts == secondListOfInts)
}
