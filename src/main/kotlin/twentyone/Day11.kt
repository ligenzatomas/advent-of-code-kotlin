package twentyone

class Day11 {
    init {
        partOne(true)
    }

    fun partOne(partTwo: Boolean) {
        val inputs = Utils()
            .parseLines("11")
            .toList()
            .map { it.chunked(1) { charSequence ->  Octopus(Integer.parseInt(charSequence[0].toString())) } }
            .toList()

        var i = 1
        var all = false
        var result = 0
        var last = 0

        while (!all && i <= 1000) {
            inputs.forEach { list ->
                list.forEach { octopus ->
                    octopus.step()
                }
            }

            inputs.forEachIndexed { y, list ->
                list.forEachIndexed { x, octopus ->
                    if (octopus.flash()) {
                        flashOctopus(x, y, inputs)
                    }
                }
            }

            all = inputs.all { it.all { octopus -> octopus.isFlash() } }
            last = result

            inputs.forEach { list ->
                list.forEach { octopus ->
                    result += octopus.reset()
                }
            }

            i++
            println("$result $i $all " + (result-last))
        }

        println(i)
    }

    fun flashOctopus(x: Int, y: Int, inputs: List<List<Octopus>>) {
        if (x > 0 && y > 0) {
            if (inputs[y-1][x-1].flashStep()) {
                flashOctopus(x-1, y-1, inputs)
            }
        }

        if (y > 0) {
            if (inputs[y-1][x].flashStep()) {
                flashOctopus(x, y-1, inputs)
            }
        }

        if (x < 9 && y > 0) {
            if (inputs[y-1][x+1].flashStep()) {
                flashOctopus(x+1, y-1, inputs)
            }
        }

        if (x > 0) {
            if (inputs[y][x-1].flashStep()) {
                flashOctopus(x-1, y, inputs)
            }
        }

        if (x < 9) {
            if (inputs[y][x+1].flashStep()) {
                flashOctopus(x+1, y, inputs)
            }
        }

        if (x > 0 && y < 9) {
            if (inputs[y+1][x-1].flashStep()) {
                flashOctopus(x-1, y+1, inputs)
            }
        }

        if (y < 9) {
            if (inputs[y+1][x].flashStep()) {
                flashOctopus(x, y+1, inputs)
            }
        }

        if (x < 9 && y < 9) {
            if (inputs[y+1][x+1].flashStep()) {
                flashOctopus(x+1, y+1, inputs)
            }
        }
    }

    data class Octopus(var energyLevel: Int) {
        var flash = false

        fun isFlash(): Boolean {
            return flash
        }

        fun reset(): Int {
            if (flash) {
                energyLevel = 0
                flash = false
                return 1
            }

            flash = false
            return 0
        }

        fun flashStep(): Boolean {
            energyLevel++

            if (energyLevel > 9 && flash == false) {
                flash = true
                return true
            } else {
                return false
            }
        }

        fun flash(): Boolean {
            if (energyLevel > 9 && !flash) {
                flash = true
                return true
            }

            return false
        }

        fun step() {
            energyLevel++
        }
    }
}