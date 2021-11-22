package part2Oop

object Generics extends App {

  class MyList[A]

  class MyMap[Key,Value]

  // Companion to class
  object MyList {
    def empty[A]: MyList[A] = ???
  }
  val emptyListOfInts = MyList.empty[Int]

  // variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // Question does list of Cat extend list of Animal?

  // 1. Yes. (Covariance)
  class CovariantList[+A]
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]

  // Can you add any animal to it?
  //  animalList.add(new Dog)

  // 2. No. (Invariance)
  class InvariantList[A]
  // Will break
//  val invariantList: InvariantList[Animal] = new InvariantList[Cat]

  // 3. Hell, no! (Contravariance)
  // This reads I need a Cat trainer but I'm being supplied an Animal trainer who can train any animal
  class Trainer[-A]
  val contravariantList: Trainer[Cat] = new Trainer[Animal]

  // Bounding types
  class Cage[ A <: Animal] (animal: A)
  val cage = new Cage(new Cat)

  // Must be within type bounds (this is an upper bounded types)
  class Car
//  val newCage = new Cage(new Car)

  abstract class MyListCov[+A] {

    // This will not compile because if you add a dog to a list of cats you get a list of animals
    // So we need to expect that the return type may change

//    def add(element: A): MyListCov[A]

    // The answer to the hard question for covariance is to promote the Cat list to an Animal list
    // Ex. A = Cat, B = Dog = Animal
    def add[B >: A](element: B): MyListCov[B]
  }
}
