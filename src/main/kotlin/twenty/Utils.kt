package twenty

import java.io.File

class Utils {

    fun parseLines(day: String): List<String> = readFileAsLinesUsingUseLines(
        "/home/cml/Dropbox/docMy/workspace/AdventOfCode/src/main/resources/day$day.txt"
    )

    private fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toList() }
}