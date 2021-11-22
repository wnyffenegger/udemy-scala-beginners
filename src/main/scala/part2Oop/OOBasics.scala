package part2Oop

object OOBasics extends App {

  val person = new Person("John", 26)
  println(person.age)
  println(person.x)

  person.greet("Daniel")
  person.greet()

  val author = new Writer("Charles", "Dickens", 1812)
  val novel = new Novel("Great Exepctations", 1861, author)

  println(novel.authorAge())
  println(novel.isWrittenBy(author))
  println(novel.isWrittenBy(new Writer("Charles", "Dickens", 1813)))

  val counter = new Counter
  counter.increment().increment().decrement(2).print()
}

// A class parameter has no var or val, it is just a parameter to the constructor
//class Person(name: String, age:Int)

// To convert to an instance field add val or var
//class Person(name: String, val age:Int)

class Person(name: String, val age:Int) {
  val x = 2

  println(1 + 3)

  // Not a class field but still available
  def greet(name: String): Unit = println(s"${this.name} says: Hi, $name")

  // This is implied unless there is a conflict
  def greet(): Unit = println(s"Hi, I am $name")

  // Constructor overloading
  def this(name: String) = this(name, 0)
  def this() = this("John Doe")

}

/*
 * Writer: first name, surnmame, year of birth
 *      - method fullname
 * Novel: name, year of release, author
 *    authorAge
 *    isWrittenBy(author)
 *    copy (new year of release)
*/

class Writer(val firstName: String, val surname: String, val yearBorn: Int) {
  def fullname(): String = {
    firstName + " " + surname
  }
}

class Novel(val name: String, val releasedOn: Int, val author: Writer) {
  def authorAge(): Int = {
    this.releasedOn - this.author.yearBorn
  }

  def isWrittenBy(author: Writer): Boolean = {
    this.author.surname == author.surname && this.author.firstName == author.firstName
  }

  def copy(newRelease: Int): Novel = {
    new Novel(name, newRelease, author)
  }
}

/*
  Counter class
    - receives an int value
    - method current count
    - method to increment/decrement => new Counter
*/

class Counter(val count: Int = 0) {

  def increment(amount: Int): Counter = {
    new Counter(count + amount)
  }

  def increment(): Counter = {
    this.increment(1)
  }

  def decrement(amount: Int): Counter = {
    new Counter(count - amount)
  }

  def decrement(): Counter = {
    this.decrement(1)
  }

  def print(): Unit = {
    println(count)
  }
}

