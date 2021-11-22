package part2Oop

import java.time.DayOfWeek

object Inheritance extends App {

  sealed class Animal {
    protected val creatureType = "wild"
    def eat = println("hungry")
  }

  class Cat extends Animal {
    def crunch = {
      eat
      println("crunch crunch")
    }
  }

  val cat = new Cat
  cat.crunch

  class Person(name: String, age:Int) {
    def this(name: String) = this(name, 0)
  }
  class Adult(name: String, age: Int, idCard: String) extends Person(name, age)

  //Overriding
  class Dog(override val creatureType: String) extends Animal {
//    override val creatureType = "domestic"
    override def eat = println("crunch, crunch")
  }

  val dog = new Dog("K9")
  dog.eat
  println(dog.creatureType)

  // polymorphism via type substitution
  val unknownAminal: Animal = new Dog("K9")
  unknownAminal.eat

  // preventing overrides
  // using the final keyword on the class or field or method
  // seal the class = extend classes in the current file but prevent extension in other files


}
