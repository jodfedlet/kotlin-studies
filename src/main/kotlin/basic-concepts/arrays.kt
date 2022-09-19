
fun main(){
    val cars = arrayOf("Kwid", "BMW", "Ford", "Mazda")
    for (car in cars) {
        println(car)
    }
    println("*****************")
    println(cars[0])
    cars[0] = "Renault"
    println(cars[0])
    println(cars.size)

    if ("BMW" in cars) {
        println("It exists")
    } else {
        println("It not exists")
    }
}