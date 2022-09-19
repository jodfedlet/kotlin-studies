
fun main() {
    var greeting: String = "Hello world"
    val stringLength = greeting.length
    println(greeting[0])
    println(stringLength)
    println(greeting[stringLength - 1])

    println(greeting.toUpperCase())
    println(greeting.toLowerCase())

    val greeting2 = "hello world"

    println(greeting.compareTo(greeting2))

    var txt = "Please locate where 'locate' occurs!"
    println(txt.indexOf("locate"))

    //concatenation
    val name = "Jod "
    val nickname ="Le Rêveur"
    println(name+nickname)
    println(name.plus(nickname))

    //interpolation/template
    println("I am $name $nickname")
}