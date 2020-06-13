fun main() {
    val timerValue = readLine()!!.toInt()
    val timer = ByteTimer(timerValue)
    println(timer.time)
}

class ByteTimer(time: Int) {
    var time: Int = time
        get() {
            field = when {
                field < -128 -> -128
                field > 127 -> 127
                else -> field
            }
            return field
        }
}
