package twentyone

class Day03 {
    init {
        //firstPart()
        secondPart()
    }

    fun firstPart() {
        val inputs = Utils().parseLines("03").toList()

        val countOfLines = inputs.size
        val positionCount = arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

        for (line in inputs) {
            for (index in line.indices) {
                if (line[index] == '1') {
                    positionCount[index]++
                }
            }
        }

        println(positionCount)

        var gammaRate = ""
        var epsilonRate = ""

        for (pos in positionCount) {
            if (pos > (countOfLines / 2)) {
                gammaRate += "1"
                epsilonRate += "0"
            } else {
                gammaRate += "0"
                epsilonRate += "1"
            }
        }

        println(gammaRate + " " + epsilonRate)

        println(gammaRate.toLong(2) * epsilonRate.toLong(2))

    }

    fun actualMost(inputs: List<String>, index: Int): Char {
        val countOfLines = inputs.size.toDouble().div(2)
        var countOne = 0

        for (line in inputs) {
            if (line[index] == '1') countOne++
        }

        if (countOne >= countOfLines) return '1'
        else return '0'
    }

    fun actualLess(inputs: List<String>, index: Int): Char {
        val countOfLines = inputs.size.toDouble().div(2)
        var countOne = 0

        for (line in inputs) {
            if (line[index] == '1') countOne++
        }

        if (countOne < countOfLines) return '1'
        else return '0'
    }

    fun secondPart() {
        var index = 0
        var listOxygen = Utils().parseLines("03").toList()
        var actualMost = actualMost(listOxygen, index)

        while (listOxygen.filter { return@filter it[index] == actualMost }.size >= 1) {
            listOxygen = listOxygen.filter { it[index] == actualMost }

            if (listOxygen.size == 1) break

            index++
            actualMost = actualMost(listOxygen, index)
        }

        index = 0
        var listCO2 = Utils().parseLines("03").toList()
        var actualLess = actualLess(listCO2, index)

        while (listCO2.filter { return@filter it[index] == actualLess }.size >= 1) {
            listCO2 = listCO2.filter { it[index] == actualLess }

            if (listCO2.size == 1) break

            index++
            actualLess = actualLess(listCO2, index)
        }

        println(listOxygen.get(0) + " " + listCO2.get(0))

        println(listOxygen.get(0).toLong(2) * listCO2.get(0).toLong(2))
    }
}