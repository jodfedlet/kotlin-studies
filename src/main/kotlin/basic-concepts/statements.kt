
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


    //WHEN --> similar to SWITCH in JAVA
    val day: Int = 5

    val res = when(day) {
        1 -> "Monday"
        2 -> "Tuesday"
        3 -> "Wednesday"
        4 -> "Thursday"
        5 -> "Friday"
        6 -> "saturday"
        7 -> "Sunday"
        else -> "Invalid day"
    }
    println(res)
}