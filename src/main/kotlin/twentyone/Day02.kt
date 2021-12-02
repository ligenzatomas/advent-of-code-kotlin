package twentyone

class Day02 {

    init {
        secondPart()
    }

    fun secondPart() {
        val inputs = Utils().parseLines("02").toList()

        var x = 0
        var y = 0
        var aim = 0

        inputs.forEach {
            val word = "^([a-zA-Z]*)".toRegex().find(it)?.value?.toString()
            val value = "^[a-zA-Z]* ([0-9]*)".toRegex().find(it)?.groupValues?.get(1)?.toInt()
            println(word)
            println(value)
            if (word.equals("forward")) {
                if (value != null) {
                    x += value
                    y += aim * value
                }
            }

            if (word.equals("up")) {
                if (value != null) {
                    aim -= value
                }
            }

            if (word.equals("down")) {
                if (value != null) {
                    aim += value
                }
            }
        }

        println(x * y)
    }

    fun firstPart() {
        val inputs = Utils().parseLines("02").toList()

        var x = 0
        var y = 0

        inputs.forEach {
            val word = "^([a-zA-Z]*)".toRegex().find(it)?.value?.toString()
            val value = "^[a-zA-Z]* ([0-9]*)".toRegex().find(it)?.groupValues?.get(1)?.toInt()
            println(word)
            println(value)
            if (word.equals("forward")) {
                if (value != null) {
                    x += value
                }
            }

            if (word.equals("up")) {
                if (value != null) {
                    y -= value
                }
            }

            if (word.equals("down")) {
                if (value != null) {
                    y += value
                }
            }
        }

        println(x * y)
    }
}