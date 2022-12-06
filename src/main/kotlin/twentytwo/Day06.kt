package twentytwo

import java.io.BufferedReader
import java.io.FileReader

class Day06 {
    init {
        firstPart()
        secondPart()
    }

    // 1707
    private fun firstPart() {
        val br = BufferedReader(FileReader("/home/cml/Dropbox/docMy/workspace/AdventOfCode/src/main/resources/2022/day06.txt"))
        var line: String?
        br.readLine().also { line = it }

        for (i in 3 until line!!.length) {
            if (line!!.toCharArray(i-3, i+1).toSet().size == 4) {
                println(i+1)
                break
            }
        }
    }

    // 3697
    private fun secondPart() {
        val br = BufferedReader(FileReader("/home/cml/Dropbox/docMy/workspace/AdventOfCode/src/main/resources/2022/day06.txt"))
        var line: String?
        br.readLine().also { line = it }

        for (i in 13 until line!!.length) {
            if (line!!.toCharArray(i-13, i+1).toSet().size == 14) {
                println(i+1)
                break
            }
        }
    }
}