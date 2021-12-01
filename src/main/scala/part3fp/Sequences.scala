package part3fp

import scala.util.Random

object Sequences extends App {
  val aSequence = Seq(1,3,2,4)
  println(aSequence)
  println(aSequence.reverse)

  // Apply method is a get
  println(aSequence(2))
  println(aSequence ++ Seq(5,6,7))

  // Sorts if type is by default ordered or you can provide the comparator
  println(aSequence.sorted)

  val aRange: Seq[Int] = 1 to 10
  aRange.foreach(println)

  val aList = List(1,2,3)

  val prepended = 42 +: aList
  val appended = aList :+ 89
  println(prepended)

  // Constructs a list with n times of the value provided
  val apples5 = List.fill(5)("apple")
  println(apples5)

  // Reduce down to single string with delimiter
  println(aList.mkString("-"))

  val numbers = Array(1,2,3,4)
  val threeElements = Array.ofDim[Int](3)

  // Ints doubles and booleans get default values
  threeElements.foreach(println)

  numbers(2) = 0
  println(numbers.mkString(" "))

  // Relationship between array and sequences
  val numbersSeq: Seq[Int] = numbers // implicit conversion between Array and super type Seq
  println(numbersSeq)

  // Vectors
  val vector: Vector[Int] = Vector(1,2,3)
  println(vector)


  val maxRuns = 1000
  val maxCapacity = 1_000_000
  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), 0)
      System.nanoTime() - currentTime
    }
    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  println(getWriteTime(numbersList))
  println(getWriteTime(numbersVector))

}
