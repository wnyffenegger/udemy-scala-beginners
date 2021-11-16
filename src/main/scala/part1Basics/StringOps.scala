package part1Basics

object StringOps extends App {

  val str: String = "Hello, I am learning Scala"

  println(str.charAt(2))
  println(str.replace(" ", ""))
  println(str.substring(7, 11))
  println(str.split(" ").toList)

  val aNumberString = "2"
  val aNumber = aNumberString.toInt

  // Append and prepend are scala specific but look to be immutable
  println('a' +: aNumberString :+ 'z')

  // Scala provides operators on strings following functional paraidm
  println(str.reverse)
  println(str.take(2))

  val name = "David"
  val age = 12
  val greeting = s"Hello, my name is $name and I am $age years old"
  val anotherGreeting = s"Hello, my name is $name and I will be turning ${age + 1} years old"

  println(greeting)
  println(anotherGreeting)

  val speed = 1.2f
  val myth = f"$name can eat $speed%2.2f burgers per minute"
  println(myth)

  // raw interpolator
  println(raw"This is a \n newline")
  println("This is a \n newline")

  val escaped = "This is a \n newline"
  println(raw"$escaped")
}
