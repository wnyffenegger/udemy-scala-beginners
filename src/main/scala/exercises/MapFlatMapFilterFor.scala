package exercises

/**
 * 1. Does MyList support for comprehensions?
 * 2. Implement of a small collection of at most one element - Maybe[+T]
 *    - map, flatMap, filter
 */

object MapFlatMapFilterFor extends App {

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

    def foreach(f: A => Unit): Unit
    def sort(f: (A, A) => Int): MyList[A]
    def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C]
    def fold[B](start: B)(f: (B, A) => B): B
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

    // HOFs

    def foreach(f: Nothing => Unit): Unit = {}

    override def sort(f: (Nothing, Nothing) => Int): MyList[Nothing] = Empty

    override def zipWith[B, C](list: MyList[B], f: (Nothing, B) => C): MyList[C] = {
        if (!list.isEmpty) throw new RuntimeException("Lists do not have the same length")
        else Empty
    }

    override def fold[B](start: B)(f: (B, Nothing) => B): B = start
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

    // HOFs and Curries exercises

    override def foreach(f: A => Unit): Unit = {
      f(head)
      tail.foreach(f)
    }

    override def sort(f: (A, A) => Int): MyList[A] = {
      tail.filter(x => f(h, x) > 0).sort(f) ++
        tail.filter(x => f(h, x) <= 0).sort(f).add(head)
    }

    def zipWith[B, C](lb: MyList[B], f: (A, B) => C): MyList[C] = {
      if (lb == Empty) throw new RuntimeException("Lists do not have the same length")
      else new Cons(f(head, lb.head), tail.zipWith(lb.tail, f))
    }

    override def fold[B](start: B)(f: (B, A) => B): B = {
      tail.fold(f(start, head))(f)
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

  val double: Int => Int = 2 * _
  println(listOfInts.map(double))

  val mod2: Int => Boolean = _ % 2 == 0
  println(listOfInts.filter(mod2))

  val doubleSize: Int => MyList[Int] = (element: Int) => new Cons(element, new Cons(element, Empty))
  println(listOfInts.flatMap(doubleSize))

  val multipleAnon: Int => Int => Int = v1 => v2 =>  v1 * v2
  println(multipleAnon(2)(3))


  // HOFs and Curries Exercsises

  listOfInts.foreach(println)
  println(listOfInts.sort((x, y) => y - x))

  val zipOfStrings = new Cons("Hello", new Cons("World", Empty))
  val zipOfInts = new Cons(1, new Cons(2, Empty))
  val zipped: MyList[String] = zipOfInts.zipWith[String, String](zipOfStrings, _ + "-" + _)
  println(zipped)

  println(listOfInts.fold(10)(_ + _))

  println("For exercises")

  // Does my list support for comprehensions
  for {
    n <- listOfInts
    s <- listOfStrings
  } println(n + s)
}
