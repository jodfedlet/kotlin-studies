
fun main(){
    println(myFunction())
}

fun myFunction(): Int{
    println("Executing myFunction")
    return otherFunction(4,9)
}

fun otherFunction(num: Int, value: Int): Int {
    return shorterFunction(num, value);
}

fun shorterFunction(x: Int, y: Int) = x + y;