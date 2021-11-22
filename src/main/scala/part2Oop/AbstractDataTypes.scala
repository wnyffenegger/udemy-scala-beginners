package part2Oop

import part2Oop.Inheritance.Dog

object AbstractDataTypes {

  abstract class Animal {
    val creatureType: String
    def eat: Unit
  }

  class Cat extends Animal {
    override val creatureType: String = "domestic"

    def eat: Unit = println("Crunch crunch")
  }

  val cat = new Cat()
  cat.eat

  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  class Crocodile extends Animal with Carnivore {
    override val creatureType: String = "croc"
    def eat: Unit = println("nomnomnom")
    def eat(animal: Animal): Unit = println(s"I'm a croc and I'm eating ${animal.creatureType}")
  }

  val croc = new Crocodile
  croc.eat(cat)
}
