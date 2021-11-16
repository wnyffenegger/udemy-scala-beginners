
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

