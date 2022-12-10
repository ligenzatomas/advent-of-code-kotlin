package twentytwo

class Day10 {
    init {
        firstPart()
        secondPart()
    }

    // 13720
    private fun firstPart() {
        val inputs = Utils().parseLines("10")

        var registerX = 1
        var sum = 0
        var change: Change? = null
        val iterator = inputs.iterator()

        for (i in 1 until 221) {
            if (change == null) {
                if (iterator.hasNext()) {
                    val line = iterator.next()

                    if (line.startsWith("addx")) {
                        change = Change(line.substring(5, line.length).toInt(), i+1)
                    }
                }
            }

            if (i == 20 || i == 60 || i == 100 || i == 140 || i == 180 || i == 220) {
                sum += (i * registerX)
            }

            if (change != null && change.cycle == i) {
                registerX += change.delta
                change = null
            }
        }

        println(sum)
    }

    // FBURHZCH
    private fun secondPart() {
        val inputs = Utils().parseLines("10")

        var registerX = 1
        var change: Change? = null
        val iterator = inputs.iterator()
        var position = 0
        var line = 1

        for (i in 0 until 241) {
            if (change == null) {
                if (iterator.hasNext()) {
                    val line = iterator.next()

                    if (line.startsWith("addx")) {
                        change = Change(line.substring(5, line.length).toInt(), i+1)
                    }
                }
            }

            if (i == 40 || i == 80 || i == 120 || i == 160 || i == 200 || i == 240) {
                print("\n")
                position = 0
            }

            if (Math.abs(position-registerX) > 1) {
                print(".")
            } else {
                print("#")
            }
            position++

            if (change != null && change.cycle == i) {
                registerX += change.delta
                change = null
            }
        }
    }

    data class Change(val delta: Int, val cycle: Int)
}