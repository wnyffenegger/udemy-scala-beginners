package part2Oop

object Objects extends App {

  // Objects do not see parameters
  object Person {
    val N_EYES = 2
    def canFly: Boolean = false
    def apply(mother: Person, father: Person): Person = new Person("Alice")
  }

  class Person(val name: String) {

  }

  println(Person.N_EYES)
  println(Person.canFly)

  val mary = Person
  val john = Person
  println(mary == john)

  val bob = new Person("Bob")
  val mike = new Person("Mike")
  println(mike == bob)
  
  // Not a constructor
  val alice = Person(bob, mike) 
}
