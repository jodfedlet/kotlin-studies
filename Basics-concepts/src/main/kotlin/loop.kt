
fun main() {

    //WHILE
    /*
    while (condition) {
        // code block to be executed
    }*/

    var i = 0
    while (i < 5) {
        println(i)
        i++
    }

    //DO WHILE
    /*
    do {
        // code block to be executed
    }
    while (condition);
     */
    var j = 0
    do {
        println(j)
        j++
    }
    while (j < 5)


    // BREAK/CONTINUE
    var k = 0
    while (k < 10) {
        println(k)
        k++
        if (k == 4) {
            break
        }
    }

    var l = 0
    while (l < 10) {
        if (l == 4) {
            l++
            continue
        }
        println(l)
        l++
    }
}