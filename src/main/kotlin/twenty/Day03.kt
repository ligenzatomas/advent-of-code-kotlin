package twenty

import java.io.File

class Day03 {

    constructor() {
        secondPart()
    }

    // 61
    // 257
    // 64
    // 47
    // 37
    fun secondPart() {
        val inputs = readFileAsLinesUsingUseLines("/home/tomasligenza/Dropbox/docMy/workspace/AdventOfCode/src/main/resources/day03.txt")
        var position = 0;
        var hit = 0;
        var first = 0;

        for (item in inputs) {
            if (first == 1) {
                first--
                continue
            } else {
                first++
            }
            if (item.toCharArray()[position] == '#') hit++;

            position += 1;

            if (position >= 31) position -= 31
        }

        println(hit)
    }

    fun firstPart() {
        val inputs = readFileAsLinesUsingUseLines("/home/tomasligenza/Dropbox/docMy/workspace/AdventOfCode/src/main/resources/day03.txt")
        var position = 0;
        var hit = 0;

        for (item in inputs) {
            print(item.toCharArray()[position])
            if (item.toCharArray()[position] == '#') hit++;

            position += 3;

            if (position >= 31) position -= 31
        }

        println(hit)
    }

    private fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toList() }
}