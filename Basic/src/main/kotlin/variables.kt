

fun main(){
    /*
    Using var, the value can be changed/modified but, using val, the value cannot
     */
    var name = "Jane Doe" // or var name: String = "Jane Doe"
    val birthyear = 1995 // or val birthyear: Int = 1995

    println(name)
    println(birthyear)

    val lastname = "Peter"
    //lastname = "Robert" // will throw an error because val variable can not be reassigned
    println(lastname)

    //Display variable

    var someVarName = "John Doe"
    println("Hello "+someVarName)

    val firstname = "John "
    val nickname = "Doe"
    val fullname = firstname + nickname
    println(fullname)

    val x = 4
    val y = 5
    print(x + y)

}