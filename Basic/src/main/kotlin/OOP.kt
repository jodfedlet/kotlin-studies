class Car {
  var brand = ""
  var model = ""
  var year = 0
}

class Car1(var brand: String, var model: String, var year: Int)

class Car2(var brand: String, var model: String, var year: Int) {
  // Class function
  fun drive() {
    println("Wrooom!")
  }

  // Class function with parameters
  fun speed(maxSpeed: Int) {
    println("Max speed is: " + maxSpeed)
  }
}


// Superclass
open class MyParentClass {
  val x = 5
}

// Subclass
class MyChildClass: MyParentClass() {
  fun myFunction() {
    println(x) // x is now inherited from the superclass
  }
}

fun main(){
  val c1 = Car()
  c1.brand = "Ford"
  c1.model = "Mustang"
  c1.year = 1969

  val c2 = Car()
  c2.brand = "BMW"
  c2.model = "X5"
  c2.year = 1999

  println(c1.brand)  // Ford
  println(c2.brand)  // BMW


  //Using construct
  val c3 = Car1("Ford", "Mustang", 1969)

  val c4 = Car1("Ford", "Mustang", 1969)
  val c5 = Car1("BMW", "X5", 1999)
  val c6 = Car1("Tesla", "Model S", 2020)



  val c7 = Car2("Ford", "Mustang", 1969)

  // Call the function
  c7.drive()
  c7.speed(200)

  //inheritance
  val myObj = MyChildClass()
  myObj.myFunction()
}