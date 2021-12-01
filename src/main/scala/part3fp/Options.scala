package part3fp

import scala.util.Random

object Options extends App {

  val myFirstOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None

  println(myFirstOption)
  println(noOption)

  // unsafe apis
  def unsafeMethod(): String = null

  // wrong
  val result = Some(unsafeMethod())

  // right
  // may return Some or None
  val resultOption = Option(unsafeMethod())
  println(resultOption)

  // chained methods with Option
  def backupMethod(): String = "A valid result"
  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))

  def betterUnsafeMethod(): Option[String] = None
  def betterBackupMethod(): Option[String] = Some("A valid result")
  val betterChainedResult = betterUnsafeMethod() orElse betterBackupMethod()


  // functions on options
  println(myFirstOption.isEmpty)
  println(myFirstOption.isDefined)

  // If option is None will throw NPE
  println(myFirstOption.get)

  // map, flatmap, filter
  println(myFirstOption.map(_ * 2))
  println(myFirstOption.filter(x => x > 10))
  println(myFirstOption.flatMap(x => Option(x * 10)))

  // for comprehensions also work

  /**
   * Exercise
   *
   *
   */

  val config: Map[String, String] = Map(
    // Fetched from elsewhere
    "host" -> "176.45.36.1",
    "port" -> "80"
  )

  class Connection {
    def connect = "Connected" // reality connect to some server
  }

  object Connection {

    val random = new Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] = {
        if (random.nextBoolean()) Some(new Connection)
        else None
    }
  }

  // try to establish a connection, is so print the connect method

  val host = config.get("host")
  val port = config.get("port")

  /**
   * if h != null
   *  if p != null
   *    return Connection.apply(h, p)
   * return null
   */
  val connection = host.flatMap(h => port.flatMap(p => Connection.apply(h,p)))

  /**
   * if c != null
   *  return c.connect
   * return null
   */
  val connectionStatus = connection.map(c => c.connect)

  /**
   * if (status != null)
   *  println(status)
   */
  println(connectionStatus)

  // Other solutions
  config.get("host")
    .flatMap(host => config.get("port")
      .flatMap(port => Connection(host, port))
       .map(connection => connection.connect))
    .foreach(println)

  val forConnectionStatus = for {
    // Given a successfully retrieved host
    host <- config.get("host")
    // Given a successfully retrieved port
    port <- config.get("port")
    // Given a successfully formed connection
    connection <- Connection(host, port)
    // Return the connection status
  } yield connection.connect
  println(forConnectionStatus)
}
