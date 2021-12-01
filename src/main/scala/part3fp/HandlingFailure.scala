package part3fp

import scala.util.{Failure, Random, Success, Try}

object HandlingFailure extends App {

  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("SUPER FAILURE"))

  println(aSuccess)
  println(aFailure)

  def unsafeMethod(): String = throw new RuntimeException("NO STRING FOR YOU BUSTER")

  val potentialFailure = Try(unsafeMethod())
  println(potentialFailure)

  val anotherPotentialFailure = Try {

  }

  // Utilities
  println(potentialFailure.isSuccess)
  println(potentialFailure.isFailure)

  def backupMethod(): String = "A valid result"
  val fallbackTry = Try(unsafeMethod()).orElse(Try(backupMethod()))
  println(fallbackTry)

  // IF you design the API and failure is possible, wrap in a Try
  def betterUnsafeMethod(): Try[String] = new Failure(new RuntimeException)
  def betterBackupMethod(): Try[String] = Success("A valid result")
  println(betterUnsafeMethod() orElse betterBackupMethod())

  // Try also implements map, flatMap, and filter
  // This leads to for comprehensions
  println(aSuccess.map(_ * 2))
  println(aSuccess.flatMap(x => Success(x * 10)))
  println(aSuccess.filter(_ > 10))

  /**
   * Exercise
   */

  val hostname = "localhost"
  val port = "8080"

  def renderHTML(page: String) = println(page)

  class Connection {
    def get(url: String): String = {
      val random = new Random(System.nanoTime())
      if (random.nextBoolean()) {
        "<html>...</html>"
      } else {
        throw new RuntimeException("Connection interrupted")
      }
    }

    def getSafe(url: String): Try[String] = Try(get(url))
  }

  object HttpService {
    val random = new Random(System.nanoTime())

    def getConnection(host: String, port: String): Connection = {
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("Port in use")
    }

    def getSafeConnection(host: String, port: String): Try[Connection] = Try(getConnection(host, port))
  }

  // if you get the html page from the connection, print it to the console i.e. call renderHTML

  for {
    con <- Try(HttpService.getConnection(hostname, port))
    page <- Try(con.get("some_url"))
  } renderHTML(page)

  // Or
  val possibleCon = HttpService.getSafeConnection(hostname, port)
  val possibleHTML = possibleCon.flatMap(connection => connection.getSafe("url"))
  possibleHTML.foreach(renderHTML)
}
