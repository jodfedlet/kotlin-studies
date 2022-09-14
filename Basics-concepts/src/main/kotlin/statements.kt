
fun main(){
    if (20 > 18) {
        println("20 is greater than 18")
    }

    val condition: Boolean = true

    if (condition) {
        // block of code to be executed if the condition is true
    } else {
        // block of code to be executed if the condition is false
    }

    val time = 20
    val greeting = if (time < 18) {
        "Good day."
    } else {
        "Good evening."
    }
    println(greeting)

    val greeting2 = if (time > 18) "Good day!" else "Good Evening"
    println(greeting2)
}