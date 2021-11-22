package part2Oop

object AnonymousClasses extends App {

  abstract  trait Animal {
    def eat: Unit
  }

//  abstract  class Animal {
//    def eat: Unit
//  }

  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("hahahaha")
  }

  println(funnyAnimal.eat)

  class Person(name: String) {
    def sayHi: Unit = println(s"Hi, my name is $name, how can I help?")
  }

  val jim = new Person("jim") {
    override def sayHi: Unit = println(s"Hi, my name is Jim, how can I be of service?")
  }
  println(jim.sayHi)
}
