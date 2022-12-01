package twentytwo

import java.io.File

class Utils {

    fun parseLinesToInt(day: String): List<Int> = parseLines(day).map { it.toInt() }

    fun parseLines(day: String): List<String> = readFileAsLinesUsingUseLines(
        "/home/cml/Dropbox/docMy/workspace/AdventOfCode/src/main/resources/2022/day$day.txt"
    )

    private fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toList() }
}