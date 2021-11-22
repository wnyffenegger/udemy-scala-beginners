package part2Oop

import scala.language.postfixOps

object MethodNotations extends App {

  class Person(val name: String, val favoriteMovie: String, val age: Int = 20) {
    def likes(movie: String): Boolean = movie == favoriteMovie
    def hangOutWith(person: Person): String = s"$name is hanging out with ${person.name}"
    def +(person: Person): String = s"$name is hanging out with ${person.name}"
    def +(nickname: String): Person = new Person(s"$name ($nickname)", favoriteMovie, age)
    def unary_! : String = s"$name, what the heck"
    def unary_+ : Person = new Person(name, favoriteMovie, age + 1)
    def isAlive: Boolean = true

    def learns(learning: String): String = s"$name learns $learning"
    def learnsScala : String = learns("Scala")

    def apply(): String = s"Hi, my name is $name and my favorite movie is $favoriteMovie"
    def apply(times: Int): String = s"$name watched $favoriteMovie $times times"
  }

  val mary = new Person("Mary", "Inception")

  // Infix notation, only works with methods with one parameter
  println(mary likes "Inception" )

  val tom = new Person("Tom", "Fight Club")
  println(mary hangOutWith tom)
  println(mary + tom)

  // All operators are methods
  println(1.+(2))

  // Prefix notation
  // Only works with + - ~ !
  val x = -1
  val y = 1.unary_-
  println(!mary)
  println(mary.unary_!)


  // postfix notation
  println(mary isAlive)
  println(mary.isAlive)

  // special method name apply
  println(mary.apply())
  println(mary())

  // 1
  println((mary + "the rockstar").name)

  // 2
  println((+mary).age)

  // 3 & 4
  println(mary learnsScala)

  // 5
  println(mary(2))
}

/*
  1. Overload the + operator to add a nickname
  2. Add an age to the Person class and add a unary + operator which is new person age + 1
  3. Add a learns method in the person class => Mary learns Scala
  4. Add a learnsScala method which calls learns method
  5. Add another apply method that receives a number and returns a string "Mary watched Inception 2 times"
*/
