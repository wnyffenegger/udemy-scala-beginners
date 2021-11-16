package part1Basics

object DefaultArgs extends App {

  def trFact(n: Int, acc: Int = 1): Int = {
    if (n <= 1) acc
    else trFact(n - 1, n * acc)
  }

  val fact10 = trFact(10)

  def savePicture(format: String = "jpg", width: Int = 19, height: Int = 20): Unit = println("saving picture")

  // Will not compile because compiler can't figure out which var thi is meant to go to
//  savePicture(800, 600)
  savePicture(width = 800)
  savePicture(width = 800, height = 800, format = "bitmap")

  /*
   * 1. Pass in every leading argument
   * 2. Name every argument
   * A side effect of this
  */
}
