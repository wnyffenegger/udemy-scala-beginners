package part3fp

object TuplesAndMaps extends App {

  val aTuple = (2, "hello, scala")

  println(aTuple._1)
  println(aTuple.copy(_2 = "goodbye Java"))
  println(aTuple.swap)

  val aMap: Map[String, Int] = Map()

  val phonebook = Map(("Jim", 555), ("Daniel", 789))
  val phonebook2 = Map("Jim" -> 555, "Daniel" -> 789)
  println(phonebook2)

  val newPairing = "Mary" -> 678
  val newPhonebook = phonebook + newPairing
  println(phonebook)
  println(newPhonebook)

  // Functionals on maps have to be over the entries by default
  println(phonebook.map(pair => pair._1.toLowerCase -> pair._2))

  // Can be done over keys
  println(phonebook.view.filterKeys(x => x.startsWith("J")).toMap)
  println(phonebook.view.mapValues(number => number * 10).toMap)


  // how to convert
  println(phonebook.toList)
  println(List(("Daniel", 555)).toMap)
  val names = List("Bob", "James", "Angela", "Mary", "Jim")
  println(names.groupBy(name => name.charAt(0)))


  /**
   * 1. What would happen if a map had two values mapping to one key
   * 2. Overly simplified social network based on maps
   *    Person = String
   *    - add a person to the network
   *    - remove
   *    - friend
   *    -unfriend
   *    - number of frieds of a given person
   *    - person with most friends
   *    - how many people have no frieds
   *    - is there a social connection between two people (direct or not)
   */

  // 1
  val duplicatePhonebook = phonebook + ("Jim" -> 444)
  println(duplicatePhonebook)

  case class Network(var network: Map[String, Set[String]] = Map()) {

    def add(person: String): Network = {
      network = network + (person -> Set())
      Network(network)
    }

    def friend(person1: String, person2: String): Network = {

      val friends1 = network(person1)
      val friends2 = network(person2)

      Network(network + (person1 -> (friends1 + person2)) + (person2 -> (friends2 + person1)))
    }

    def unfriend(person1: String, person2: String): Network = {
      val network2 = network.updated(person1, network(person1) - person2)
        .updated(person2, network(person2) - person1)
      Network(network2)
    }

    def friends(person1: String): Int = {
      network.get(person1).get.knownSize
    }

    def remove(person: String): Network = {
      def removeFriended(friends: Set[String], accumulator: Network): Network = {
        if (friends.isEmpty) accumulator
        else removeFriended(friends.tail, accumulator.unfriend(person, friends.head))
      }
      removeFriended(network(person), this)
    }

    def friendless(): Int = {
      network.count(pair => pair._2.isEmpty)
    }

    def mostFriends(): String = {
      network.maxBy(pair => pair._2.size)._1
    }

    def connected(person1: String, person2: String): Boolean = {
      def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
        if (discoveredPeople.isEmpty) false
        else {
          val person = discoveredPeople.head
          if (person == target) true
          else if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
          else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
        }
      }

      bfs(person2, Set(), network(person1))
    }
  }

  var network = Network().add("Jim").add("Bob").add("Mary")
    .friend("Jim", "Bob")
    .friend("Jim", "Mary")
  println(network)
  println(network.friends("Jim"))
  println(network.unfriend("Jim", "Bob"))

  println(network.remove("Jim"))
  println(network.mostFriends())
  println(network.friendless())
  println(network.connected("Bob", "Jim"))
  println(network.connected("Bob", "Mary"))
  println(network.connected("Mary", "Bob"))
}
