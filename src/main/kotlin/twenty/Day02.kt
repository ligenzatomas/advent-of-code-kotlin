package twenty

import java.io.File

class Day02 {
    constructor() {
        firstPart()
        secondPart()
    }

    fun secondPart() {
        val inputs = readFileAsLinesUsingUseLines("/home/tomasligenza/Dropbox/docMy/workspace/AdventOfCode/src/main/resources/day02.txt")
        var i = 0;
        inputs.forEach {
            val position1 = "^(\\d*)".toRegex().find(it)?.value?.toInt()!! - 1
            val position2 = "^\\d*-(\\d*).*$".toRegex().find(it)?.groupValues?.get(1)?.toInt()!! - 1
            val char = "^\\d*-\\d* (\\w):.*$".toRegex().find(it)?.groupValues?.get(1)?.toCharArray()?.get(0)!!
            val password = "^\\d*-\\d* \\w: (.*)$".toRegex().find(it)?.groupValues?.get(1)!!

            val epos1 = password.toCharArray()[position1] == char
            val epos2 = password.toCharArray()[position2] == char

            //println("$password[$position1] $password[$position2]")

            if (epos1.xor(epos2)) {
                i++
            } else {
                println(it)
            }
        }
        print(i)
    }

    fun firstPart() {
        val inputs = readFileAsLinesUsingUseLines("/home/tomasligenza/Dropbox/docMy/workspace/AdventOfCode/src/main/resources/day02.txt")
        var i = 0;
        inputs.forEach {
            val minimum = "^(\\d*)".toRegex().find(it)?.value?.toInt()
            val maximum = "^\\d*-(\\d*).*$".toRegex().find(it)?.groupValues?.get(1)?.toInt()
            val char = "^\\d*-\\d* (\\w):.*$".toRegex().find(it)?.groupValues?.get(1)?.toCharArray()?.get(0)
            val password = "^\\d*-\\d* \\w: (.*)$".toRegex().find(it)?.groupValues?.get(1)
            val count = password?.count { it.equals(char) }

            //println(count)

            if (count != null && minimum != null && maximum != null && (count >= minimum && count <= maximum)) {
                i++;
            }
        }
        print(i)
    }

    private fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toList() }
}