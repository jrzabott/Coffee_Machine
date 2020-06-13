fun main() {
    val input = readLine()!!

    if (input == null || input.isEmpty()) {
        println()
        return
    }

    val hasI = checkI(input)
    val hasS = checkS(input)

    if (!hasI && !hasS) {
        println("$input ")
    }
}

fun checkI(input: String): Boolean {
    var text = input

    if (!(text.first().toString() == "i")) {
        return false
    }
        text = text.drop(1)
        val inputToInt = text.toInt() + 1
        println(inputToInt)
    return true
}

fun checkS(input: String): Boolean {
    var text = input

    if (!(text.first().toString() == "s")) {
        return false
    }
    text = text.drop(1)
    println(text.reversed())
    return true
}
