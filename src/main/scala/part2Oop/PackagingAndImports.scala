package part2Oop

// Can alias class names
import playground.{Cinderella => Princess, PrinceCharming}
//import playground._

object PackagingAndImports extends App{

  // package members are available by their simple name
  val writer = new Writer("Daniel", "RockTheJVM", 2018)

//  val princess = new Cinderella
  val princess2 = new playground.Cinderella // fully qualified name

  // package follow Java specification

  // package object
  sayHello
  println(SPEED_OF_LIGHT)

  val prince = new PrinceCharming
}
