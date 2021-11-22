package part2Oop

object CaseClasses extends App {

  /**
   * Case classes are a useful shorthand
   */

  // 1. Class parameters are promoted to fields
  // 2. Sensible toString representation
  // 3. equals and hashCode implemented
  // 4. copy methods
  // 5. case classes have companion objects
  // 6. those companion objects have factory methods in apply
  // 7. case classes are serializable
  // 8. case classes have extractor patterns
  case class Person(name: String, age: Int)

  val jim = new Person("Jim", 34)
  println(jim.name)

  println(jim)

  val jim2 = new Person("Jim", 34)
  println(jim == jim2)

  val jim3 = jim.copy()
  println(jim == jim3)

  val jim4 = jim.copy(age = 45)
  println(jim4)

  val thePerson = Person
  val mary = Person.apply("Jim", 34)
  println(mary)

  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }


}
