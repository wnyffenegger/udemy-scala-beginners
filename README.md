
# Basics
## Vals and Variables

* `val's` are immutable
* `val's` can have their types inferred, their declaration is not necessary
* `val's` are type checked for overflow for numeric numbers and for sense
* `var's` variable are mutable
* Compiler can infer types in general and do type checks

### Syntax

* Semicolons only necessary for multiple statements on one line

## Expressions

* expressions return a value and type
* Normal Java operations apply to Scala

### Expressions vs. Statements

Instructions (DO) vs. Expressions (VALUE)

* An if statement in an imperative language will force you to set a variable to get a value
* An if expression in Scala will return a value
* **EVERYTHING in scala is an Expression!**

```scala
  val aCondition = true
  val aConditionedValue = if(aCondition) 5 else 3
  println(aConditionedValue)
  println(if(aCondition) 5 else 3)
```

### Loops Warning

Scala is functional and doesn't encourage loops. These are imperative structures.

```scala
  // NEVER WRITE THIS AGAIN.
  var i = 0
  while (i < 10) {
    println(i)
    i += 1
  }
```

### Unit & Side Effects

* Unit is equivalent to Void in other languages
* Side effects are always of type Unit
* Compiler will infer Unit
* Scala forces side effects (imperative programming) to become expressions, keeping them functional

```scala
  var aWeirdValue = (aVariable = 3)
  println(aWeirdValue)
```

### Code Blocks

* curly brace syntax
* Value is the value of the last expression and type of result corresponds to type of last expression
* Code blocks scope variables

## Functions

* Generally match Java functions
* Use a code block for multi-line functions
* Instead of loops generally use recursion
* Compiler cannot infer return type of recursive functions on its own
* Return types of functions can be `Unit` and is expected for I/O
```scala
  def aFunctionWithSideEffects(aString: String): Unit = println(aString)
```
* Functions can define functions with local scope inside themselves
```scala
  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int): Int = a + b

    aSmallerFunction(n, n - 1)
  }

  println(aBigFunction(10))
```

## Type Inference

* Compiler type checks using type inference
* Compiler can infer vals and vars
* Compiler cannot determine return type of a recursive function
  * Issue is branching caused by base case

## Recursion in Scala

* Naive recursion will cause stack frame errors
* To beat stack frame errors use tail recursion
* Scala is designed to recognize when the **last expression** in a function is a recursive call and **reuse
the current stack frame** for that recursive call.
* You can check whether a call is tail recursive by adding the annotation `@tailrec`

## Call by Name vs. Call by Value

* Call by value the exact value of an expression is computed before the function call
* Call by name calculates the value of an expression when it is used inside a function.

```scala

  def calledByValue(x: Long): Unit = {
    println("by value: " + x)
    println("by value: " + x)
  }

  def calledByName(x: => Long): Unit = {
    println("by name: " + x)
    println("by name: " + x)
  }

  // Calculated when method is called
  calledByValue(System.nanoTime())
  
  // nanoTime calculated when each println is called
  // basically you pass in a function that is computed 
  calledByName(System.nanoTime())
  
  def infinite(): Int = 1 + infinite()
  def printFirst(x: Int, y: => Int) = println(x)
  
  // Will crash because value is computed before call
  printFirst(infinite(), 34)
  
  // Will not crash because value is computed only when used
  printFirst(34, infinite())
```

## Default Args

Arguments can be assigned default values.

To resolve ambiguity:

* Pass in all required args first and put defaults at end
* Pass in arguments with the format `square(length=10)`

A side effect of the default arg support is that if you name arguments explicitly,
the order in which you provide them does not matter.


## String Operations

* Standard Java operations apply
* Scala provides other functional methods like `take` and `skip` and `reverse`
* Scala provides S-interpolators which is a different mentality than format strings. Use the `$` and `${}` syntax
    which is very similar to bash.
```scala
  val name = "David"
  val age = 12
  val greeting = s"Hello, my name is $name and I am $age years old"
  val anotherGreeting = s"Hello, my name is $name and I will be turning ${age + 1} years old"

  println(greeting)
  println(anotherGreeting)
```
* Scala also provides F-interpolators which follow the standard format string mentality but also include S-interpolator
capabilities
```scala
  val speed = 1.2f
  val myth = f"$name can eat $speed%2.2f burgers per minute"
  println(myth)
```
* Finally Scala provides a raw interpolator which ignores escape characters in String literals, but not in escaped Strings
```scala
  // Will ignore escape and print as is
  println(raw"This is a \n newline")

 // Will print on two lines
  val escaped = "This is a \n newline"
  println(raw"$escaped")
```

# OOP

## Object basics

* `class ClassName` is all you need to define a class
* To convert an expression from a constructor parameter to a class field prepend `var` or `val`
* Functions defined in a code block for a class are exposed
* Use `this` to disambiguate parameters to methods and fields in a class

### Constructor Notes

You can have multiple constructors but they are subject to specific limitations. Significantly different than Java.

The limitations of constructors make constructor overloading mostly pointless.

* Constructors must follow `def this(arg1) = this(arg1, default_arg)` format
* Overloaded constructors can only refer to another constructor

## Syntactic Sugar

* Scala also lets you overload any operators (+ - / *)
* Under the hood all Scala expressions are objects including primitives `1 + 2` is actually `1.+(2)`
* Scala offers infix, prefix, and postfix notation in limited circumstances

#### Infix notation
Infix notation allows you to write out sentences for methods with a single parameter
```scala
  class Person(val name: String, favoriteMovie: String) {
    def likes(movie: String): Boolean = movie == favoriteMovie
    def hangOutWith(person: Person): String = s"$name is hanging out with ${person.name}"
    def +(person: Person): String = s"$name is hanging out with ${person.name}"
  }

  val mary = new Person("Mary", "Inception")

  // Infix notation, only works with methods with one parameter
  println(mary likes "Inception" )

  val tom = new Person("Tom", "Fight Club")
  println(mary hangOutWith tom)
  println(mary + tom)
```

### Prefix Notation

* Unary prefix notation `_-` lets you prefix an expression with an operator/method
* Only works with + - ~ !

```scala
  def unary_! : String = s"$name, what the heck"

  // Prefix notation
  val x = -1
  val y = 1.unary_-
  println(!mary)
  println(mary.unary_!)
```

### Apply Method
 
Implementing apply implements a default method on any object

```scala
class Person {
  def apply(): String = "Hello World"
  def apply(string : String): String = string
}

println(new Person().apply())
println(new Person()())
println(new Person()("Hello World"))
```

## Scala Objects

* Scala does not have class-level functionality ("static")
* Objects do not have parameters like classes in constructors
* Objects have methods, vals, and vars
* A Scala object is a singleton instance
```scala
  object Person {
    val N_EYES = 2
    def canFly: Boolean = false
  }
  
  println(Person.N_EYES)
  println(Person.canFly)
  
  val mary = Person
  val john = Person
  println(mary == john)
```

### Companions

When a `class` and `object` with the same name exist in the same scope. So you can get singleton functionality
and regular instance functionality.

Singleton objects are often used to have factory methods that calculate


### Scala Applicatinos

Is a Scala object with a specific method. App has that signature so extending App will convert to an executable.
```scala
def main(args: Array[String]): Unit
```

## Inheritance and Traits

* use `extends` keyword
* private, protected, public apply to scala classes, however there is no package protected. No modifier translates to public
* You can override both functions and fields in inherited classes using the `override` keyword. **This includes changing
the access level of the functions and fields**
* Difference between Java and Scala is that you can override values as part of a constructor for a class
```scala
  //Overriding
  class Dog(override val creatureType: String) extends Animal {
    //    override val creatureType = "domestic"
    override def eat = println("crunch, crunch")
    }

    val dog = new Dog("K9")
    dog.eat
    println(dog.creatureType)
```
* `super` is still valid like in Java
* Use `sealed` to block extension outside the current file
```scala
  sealed class Animal {
    protected val creatureType = "wild"
    def eat = println("hungry")
  }
```

## Traits and Abstract Classes

* Abstract classes follow Java paradigm, mark with `abstract` keyword and both fields and methods can be abstract
* Traits are kind of like interfaces but treated like mixins in other languages
```scala
  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  class Crocodile extends Animal with Carnivore {
    override val creatureType: String = "croc"
    def eat: Unit = println("nomnomnom")
    def eat(animal: Animal): Unit = println(s"I'm a croc and I'm eating ${animal.creatureType}")
  }
```
* Abstract classes can:
  * Have abstract fields and methods
  * Constructors
  * Implemented fields and methods
* Traits can have
  * Abstract fields and methods
  * Constructors (new in Scala 3)
  * Implemented fields
* Differences between Traits and Abstract classes:
  * Multiple traits can be mixed in but only one class
  * traits = behavior, abstract class = "thing" (polymorphism vs. abstraction)


### Type hierarchy

* scala.Any - parent type
* scala.AnyRef - java.lang.Object, everything extends this
* scala.Null - null extends everything, you can extend
* scala.AnyVal - all primitives extend this
* scala.Nothing - means the absence of things including null and extends everything

## Generics

Scala generics are Java generics on steroids.

* Define a generic type in between brackets
* Generics works for classes and traits
```scala
class MyMap[Key, Value]

trait Compare[A]
```

### Variance

Attempt to answer:

Question does list of Cat extend list of Animal? 

A simplistic and slightly inaccurate look at Scala Generics.

While the statements below are true they do not encompass every effect of each type of variance.

1. (Yes) Covariance: subtype can substitute for super type (ex. for Dog accept Puppy)
```scala
class Dog
class Puppy extends Dog
class MyList[+T]

val list: MyList[Dog] = new MyList[Puppy]
//val list: MyList[Puppy] = new MyList[Puppy]
//val list: MyList[Puppy] = new MyList[Dog]
```
2. (No) Invariance: no inheritance relationship between super type and subtype (ex. for Dog accept nothing else)
```scala
class Dog
class Puppy extends Dog
class MyList[T]

//val list: MyList[Dog] = new MyList[Puppy]
val list: MyList[Puppy] = new MyList[Puppy]
//val list: MyList[Puppy] = new MyList[Dog]
```
3. (Heck no) Contravariance: super type can substitute for subtype (ex. for Puppy accept Dog)
```scala
class Dog
class Puppy extends Dog
class MyList[-T]
//val list: MyList[Dog] = new MyList[Puppy]
//val list: MyList[Puppy] = new MyList[Puppy]
val list: MyList[Puppy] = new MyList[Dog]
```

### Bounding Types

Types can be upper and lower bounding on types

* Use less than (upper bounding) or greater than (lower bounding) in the declaration of a class
```scala
  // Bounding types
  class Cage[ A <: Animal] (animal: A)
  val cage = new Cage(new Cat)

  // Must be within type bounds (this is an upper bounded type)
  // This will fail
  class Car
  val newCage = new Cage(new Car)

```

### The promotion question

When you use covariance it is possible that the type of a list may change.

Ex. a list of cats has a dog added to it then it should become a list of animals (if it is covariant)

See the MyList exercise for what that looks like.


## Anonymous classes

Works like what you would expect in Java.

* Works for both traits and classes
* Requires that you provide all constructor args that classes or traits may have

```scala
  abstract  trait Animal {
    def eat: Unit
  }

//  abstract  class Animal {
//    def eat: Unit
//  }

  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("hahahaha")
  }

  println(funnyAnimal.eat)

  class Person(name: String) {
    def sayHi: Unit = println(s"Hi, my name is $name, how can I help?")
  }

  val jim = new Person("jim") {
    override def sayHi: Unit = println(s"Hi, my name is Jim, how can I be of service?")
  }
  println(jim.sayHi)
```

## Case Classes

Case classes implement the default annoying stuff.

Adding case to a class or object gives you the following out of the box with no other implementation:

1. Class parameters are promoted to fields
2. Sensible toString representation out of the box
3. equals and hashCode implemented
4. copy methods
5. case classes have companion objects and those companion objects have factory methods using the apply pattern
7. case classes are serializable
8. case classes have extractor patterns which can be used for pattern matching.

## Enums

New in Scala 3. Most functionality from Java but not quite all.

1. Sealed datatype that cannot be extended
2. Add fields or methods
3. Companion objects can contain factory methods
4. You can get all values
5. Takes constructor arguments
6. You can get the value of an enum from a String (only works with no args enums)

## Exceptions

Java like exceptions

* Exceptions are expressions
* Exceptions are expressions returning a type Nothing
* Follows Java Exception hierarchy
  * Exception programmatic issue
  * Error
* Uses try/catch/finally syntax with a slight tweak
```scala
  try {
    getInt(true)
  } catch {
    case e: RuntimeException => println(s"${e} failed")
  } finally {
    println("finally")
  }
```
* Use finally for side effects (writing to a file, etc.)
* Type of try/catch/finally will be the least general class that catches the types of both the try and the catch cases

## Packages and Imports

Scala extends the Java functionality by providing a couple additional features

* Package Objects which allow defining constants or methods accessible across the entire package.
  * By convention always placed in a `package.scala` file.
  * Only one allowed per package
```scala
package object part2Oop {
  val PI: Double = 3.141592653
}
```
* Aliasing for package imports to prevent name conflicts
```scala
import java.util.{Date => UtilDate}
import java.sql.{Date => SqlDate}
```
* Using `_` to do `*` imports for a package

# Functional Programming

## What is a function?

While everything may look like a function in Scala, the JVM forces it to be an object. Syntactic
sugar hides the real implementation.

* When using `adder` it looks like `adder(2, 3)`
```scala
  val adder: ((Int, Int) => Int) = new Function2[Int, Int, Int] {
    override def apply(v1: Int, v2: Int): Int = v1 + v2
  }
```

* Scala supports `Function1` through `Function22` traits that implement 1 to 22 parameter functions
* Higher order functions receive functions as parameters or return functions
* Functions that create other functions that can be called with different parameters are called `curried` functions
```scala
  multiple(3)(4)
```

## Anonymous Functions

Compiler does its best to infer types to make code shorter and (theoretically) cleaner.

Options with Scala for functional syntax

1. Java like syntax
2. Java8 like lambda syntax in Scala
3. Syntactic sugar with inferred types
4. Underscore used in place of parameters, scala will infer in many circumstances

Limitations
1. Multiple params = parameters in parenthesis
2. Multiple uses of a single parameter = no `_`
3. Parenthesis must be used for every function even with no parameters ex. `val pi = () => 3.1415`
must be called `pi()` not `pi`
4. Scala must either be given type of expression and infer parameter types for functions or the reverse. Basically
either left hand side of the equation has types or right hand side does.

```scala
// Java like syntax
  val doubler = new Function1[Int, Int] {
    override def apply(x: Int) = x * 2
  }

  // Scala syntax
  // Anonymous function also called Lambda
  val doublerScala = (x: Int) => x * 2

  // Infer the types to make it short
  val doublerScalaShort: Int => Int = (x) => x * 2

  // multiple parameters in a lambda?
  val adder = (a: Int, b: Int) => a + b

  val adderTyped: (Int, Int) => Int = (a: Int, b: Int) => a + b

  // no parameters
  val justDoSomething: () => Int = () => 3
  // memory location of function
  println(justDoSomething)

  // function execution
  println(justDoSomething())

  // other syntactical style
  val stringToInt = { (str: String) =>
    str.toInt
  }

  // more syntactic sugar
  val niceIncrementer: Int => Int = (x: Int) => x + 1
  val niceIncShort: Int => Int = _ + 1

  // Same as (a, b) => a + b
  // but requires that types are absolutely clear
  // second one won't work
  val niceAdder: (Int, Int) => Int = _ + _
//  val niceAdder = _ + _
```

## IMPORTANT Higher Order Functions and Curries

### Higher order functions

* Functions that take functions as parameters and/or return functions as results
* We should prefer simplicity with higher order functions. Crazy
function definitions are hard to read so instead wrap each higher order
function with another higher order function if you can.
```scala
  // function that applies a function n times over a value x
  // nTimes(f, n, x)
  // nTimes(f, 3, x) = f( f( f(x) ) )

  // This looks a whole lot like math because it is
  def nTimes(f: Int => Int, n: Int, x: Int): Int = {
    if (n <= 0) x
    else nTimes(f, n - 1, f(x))
  }

  val plusOne = (x: Int) => x + 1
  println(nTimes(plusOne, 10, 1))


  // What if we could make these functions parameterizable?
  // Well Scala gives us that via returning anonymous functions
  def nTimesBetter(f: Int => Int, n: Int): (Int => Int) = {
    if (n <= 0) (x: Int) => x
    else (x: Int) => nTimes(f, n, x)
  }

  println(nTimesBetter(plusOne, 10)(1))w
```


### Curried Functions

Curried functions are functions that return other functions. Basically
function generators.

* There is no reason a curried function (a function generator) cannot return another curried function (a function generator)
* Scala provides syntactic sugar to define curried functions using methods with multiple parameter lists
```scala
  // functions with multiple parameter lists
  // can act like curried functions without the syntax
  // more syntactic sugar
  def curriedFormatter(c: String)(x: Double): String = c.format(x)


  // Why is this important?
  // We just defined a function that returns functions (curried formatter) and that function generator
  // can be customized to produce other functions (standardFormat, preciseFormat)
  val standardFormat: (Double => String) = curriedFormatter("%4.2f")
  val preciseFormat: (Double => String) = curriedFormatter("%10.8f")

  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))
```
* However, a curried function generated from another curried function
must specify the types it expects in Scala. The compiler cannot infer this.
```scala
  def curriedFormatter(c: String)(x: Double): String = c.format(x)
  val standardFormat: (Double => String) = curriedFormatter("%4.2f")

// One limitation
// You must specify the types to have curried functions working
// This will break
// val standardFormat = curriedFormatter("%4.2f")
```

## For comprehensions in Scala

* Map, flatmap, and foreach are essentially for comprehensions
* We have syntactic sugar to support for comprehensions in a readable format
  * Double nesting is denoted by arrows where precedence goes to first traversable listed
  * `yield` keyword is the result of the loop
```scala
  val forCombinations = for {
    n <- numbers
    c <- chars
    color <- colors
  } yield "" + c + n + "-" + colors
```
* To filter out add a "guard"
```scala
  // To filter out specific sections
  val forCombinationsFilter = for {
    n <- numbers if n % 2 == 0
    c <- chars
    color <- colors
  } yield "" + c + n + "-" + colors
  println(forCombinationsFilter)
```
* For side effects just end with the statement causing a side effect
```scala

  // If you want side effects it handles that too
  for {
    n <- numbers
  } println(n)
```

## Collections

### Hierarchy

* Traversable
* Iterable
* Sets, Sequences, and Maps
* Sequence -> indexed sequence, linear sequence

* Mutable sequences are found in `scala.collections.mutable` but should be avoided if possible

### Standard functionality

* Map: map, flatMap, collect
* Conversions: toArray, toList, toSeq
* size info
* tests: exists, forall
* folds: foldLeft, foldRight, reduceLeft, reduceRight
* retrieval: head, find, tail

### Sequences

General super class for ordered data structures that can be indexed

* General interface for data structures with a well defined order that can be indexed
* Support: Grouping, sorting, zipping, searching, slicing, appending

#### List

* LinearSeq immutable
* head, tail, isEmpty are fast
* other operations are O(n)

Lists are sealed so extending them really isn't possible.

#### Arrays

* Can be constructed with predefined length
* Can be updated in place (mutated)
* indexing is fast
* interop with Java arrays

Can be initialized with zero or null for all values by default.

#### Vectors

* Default implementation for immutable sequences
* Effectively constant indexed read and write O(log32(n))
* Fast addition and prepend
* Implemented as a fixed branched trie (branch factor 32)
* Good performance for large sizes

## Tuples and Maps


### Tuples
* Group things
* At most 32 members almost exactly like functional types
```scala
val aTuple = (2, "hello, world")
val aTuple2 = new Tuple(2, "hello, world")
```

### Maps

* Input is tuples (key, value)
* Declared like tuples using new or the apply method
* Can be filtered by entries or by keys
* Can be converted to and from lists of tuples

## Options

The possible absence of a value.

The correct way to use an optional is part of a chain of functional calls NOT individual calls. For example:

Wrong (imperative use)
```scala
  val host = config.get("host")
  val port = config.get("port")
  var connection: Option[Connection]
  if (host.isDefined && port.isDefined) {
    connection = Connection.apply(h, p)
  } else {
    connection = None
  }

  var connectionStatus: Option[String] = None
  if (connection.isDefined) {
    connectionStatus = Option(connection.get.connect)
  }
  println(connectionStatus)
```

Right way (functional)
```scala
  val host = config.get("host")
  val port = config.get("port")
  val connection = host.flatMap(h => port.flatMap(p => Connection.apply(h,p)))
  val connectionStatus = connection.map(c => c.connect)
```

* Not a collection
* Examples: `map.get("key")` or `list.head`
* **Options implement foreach, filter, map, and flatMap**. This means options can be used in for comprehensions
```scala
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
```


## Try, Failure, Success

Functional way of handling try catch logic for exceptions.

Data types:
* Success: if statement succeeds ex `Success(3)`
* Failure: if statement threw exception `Failure(new RuntimeException())`
* Try super type of Failure and Success

* Utility functions for isSuccess, isFailure, orElse are implemented like with Option
```scala
  def betterUnsafeMethod(): Try[String] = new Failure(new RuntimeException)
  def betterBackupMethod(): Try[String] = Success("A valid result")
  println(betterUnsafeMethod() orElse betterBackupMethod())
```

* **Try, Failure, and Success implement map, flatMap, and foreach so for comprehensions work with Try**
```scala
for {
  con <- Try(HttpService.getConnection(hostname, port))
  page <- Try(con.get("some_url"))
} renderHTML(page)
```