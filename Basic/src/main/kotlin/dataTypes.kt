
fun main(){

    val myNum = 5 //Int  from -2147483648 to 2147483647
    val myDoubleNum = 5.99 //Double
    val myLetter = "J" //Char
    val myBoolean = true //Boolean
    val myText = "Hello" //String

    //OR with specified types

    /*
    val myNum: Int = 5 //Int
    val myDoubleNum: Double = 5.99 //Double
    val myLetter: Char = "J" //Char
    val myBoolean: Boolean = true //Boolean
    val myText: String = "Hello" //String
     */

    //Other type
    val num: Byte = 100 // numbers from -127 to 127
    val myShortNum: Short = 5000 // -32768 to 32767
    val myLongNum: Long = 15000000000L // from -9223372036854775807 to 9223372036854775807
    val myFloatNum: Float = 5.75F
    val myDouble: Double = 19.99
    println(num)
    println(myShortNum)
    println(myLongNum)
    println(myFloatNum)
    println(myDouble)

    //Type conversion
    val x: Int = 5
    val y: Long = x.toLong()
    println(y)
}