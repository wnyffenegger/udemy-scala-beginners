package part2Oop

object Enums extends App {

  // 1. Sealed datatype that cannot be extended
  // 2. Add fields or methods
  // 3. Takes constructor arguments
  // 4. Companion objects can contain factory methods
  // 5. You can get all values
  // 6. You can get the value of an enum from a String (only works with no args enums)
  enum Permissions {
    case READ, WRITE, EXECUTE, NONE

    // add fields or methods
    def openDocument(): Unit =
      if (this == READ) println("opening document...")
      else println("reading not allowed")
  }

  val somePermissions: Permissions = Permissions.READ
  println(somePermissions.openDocument())

  enum PermissionsWithBits(bits: Int) {
    case READ extends PermissionsWithBits(4)
    case WRITE extends PermissionsWithBits(2)
    case EXECUTE extends PermissionsWithBits(1)
    case NONE extends PermissionsWithBits(0)
  }

  object PermissionsWithBits {
    def fromBits(bits: Int): PermissionsWithBits =
      PermissionsWithBits.NONE
  }

  val somePermsOrdinal = somePermissions.ordinal
  println(somePermsOrdinal)

  val allPermissions = Permissions.values
  println(allPermissions)

  val readPermission = Permissions.valueOf("READ")
  println(readPermission)
}
