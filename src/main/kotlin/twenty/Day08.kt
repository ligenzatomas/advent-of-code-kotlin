package twenty

class Day08 {
    var accumulator = 0
    var index = 0
    var changes = mutableSetOf<Int>()
    var thisRound = false

    constructor() {
        secondPart()
    }

    fun secondPart() {
        val inputs = Utils().parseLines("08")
        var mapBase = mutableMapOf<Int, Boolean>()

        for (i in inputs.indices) {
            mapBase.put(i, false)
        }

        var map = mapBase.toMutableMap()

        while (changes.size < 653) {
            while (map.get(index) == false) {
                //println(index.toString())

                if (index == 653) {
                    println("!!!!!!! " + accumulator)
                    return
                }

                map.replace(index, true)
                parseCommandAndChange(inputs.get(index))
            }

            //changes.add()
            println(changes)

            index = 0
            accumulator = 0
            map = mapBase.toMutableMap()
            thisRound = false
            Thread.sleep(100)
        }
    }

    fun parseCommandAndChange(line: String) {
        val result = "^(acc|jmp|nop) (\\+|\\-)(\\d*)".toRegex().find(line)!!

        if (((result.groupValues.get(1) == "jmp" || result.groupValues.get(1) == "nop")
                    && !changes.contains(index)) && !thisRound
        ) {
            changes.add(index)
            thisRound = true
            println(index)

            //println(line + " " + result.groupValues.get(1) + " " + result.groupValues.get(2) + " " + result.groupValues.get(3))
            //logger.log(Level.INFO, index.toString() + " ")

            if (result.groupValues.get(1) == "jmp") {
                index++
                return
            }

            if (result.groupValues.get(1) == "nop") {
                if (result.groupValues.get(2) == "+") {
                    index += result.groupValues.get(3).toInt()
                } else {
                    index -= result.groupValues.get(3).toInt()
                }
                return
            }
        }

        if (result.groupValues.get(1) == "acc") {
            index++
            if (result.groupValues.get(2) == "+") {
                accumulator += result.groupValues.get(3).toInt()
            } else {
                accumulator -= result.groupValues.get(3).toInt()
            }
        }

        if (result.groupValues.get(1) == "jmp") {
            if (result.groupValues.get(2) == "+") {
                index += result.groupValues.get(3).toInt()
            } else {
                index -= result.groupValues.get(3).toInt()
            }
        }

        if (result.groupValues.get(1) == "nop") {
            index++
        }
    }

    fun firstPart() {
        val inputs = Utils().parseLines("08")
        val map = mutableMapOf<Int, Boolean>()

        for (i in inputs.indices) {
            map.put(i, false)
        }

        while (map.get(index) == false) {
            println(index.toString())
            map.replace(index, true)
            parseCommand(inputs.get(index))


        }

        println(accumulator)
    }

    fun parseCommand(line: String) {
        val result = "^(acc|jmp|nop) (\\+|\\-)(\\d*)".toRegex().find(line)!!

        println(line + " " + result.groupValues.get(1) + " " + result.groupValues.get(2) + " " + result.groupValues.get(3))

        if (result.groupValues.get(1) == "acc") {
            index++
            if (result.groupValues.get(2) == "+") {
                accumulator += result.groupValues.get(3).toInt()
            } else {
                accumulator -= result.groupValues.get(3).toInt()
            }
        }

        if (result.groupValues.get(1) == "jmp") {
            if (result.groupValues.get(2) == "+") {
                index += result.groupValues.get(3).toInt()
            } else {
                index -= result.groupValues.get(3).toInt()
            }
        }

        if (result.groupValues.get(1) == "nop") {
            index++
        }
    }
}