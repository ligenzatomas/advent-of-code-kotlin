package twenty

import java.io.File

class Day01 {
    constructor() {
        firstPart();
        secondPart();
    }

    /**
     * Result is 96047280, Counts are 2 and 391 and 65792
     */
    private fun secondPart() {
        val inputs = readFileAsLinesUsingUseLines("/home/tomasligenza/Dropbox/docMy/workspace/AdventOfCode/src/main/resources/day01.csv")
        val time = System.currentTimeMillis()
        val intsAscending = inputs.map { it.toInt() }.sorted()
        val intsDescending = intsAscending.sortedDescending()

        var countAsc = 0;
        var countDesc = 0;
        var countDesc2 = 0;

        intsAscending.forEach asc@{ asc ->
            countAsc++
            intsDescending.forEach desc@{ desc ->
                countDesc++
                if (asc + desc > 2020) return@desc

                intsDescending.forEach desc2@{ desc2 ->
                    countDesc2++
                    if (asc + desc + desc2 == 2020) {
                        println(System.currentTimeMillis() - time)
                        println("Result is ${asc * desc * desc2}")
                        println("Counts are ${countAsc} and ${countDesc} and ${countDesc2}")
                        return
                    }
                    if (asc + desc + desc2 > 2020) return@desc2
                }
            }
        }
    }

    /**
     * Result is 970816, Counts are 8 and 1589
     */
    private fun firstPart() {
        val inputs = readFileAsLinesUsingUseLines("/home/tomasligenza/Dropbox/docMy/workspace/AdventOfCode/src/main/resources/day01.csv")
        val intsAscending = inputs.map { it.toInt() }.sorted()
        val intsDescending = intsAscending.sortedDescending()

        var countAsc = 0;
        var countDesc = 0;

        intsAscending.forEach { asc ->
            countAsc++
            intsDescending.forEach desc@{ desc ->
                countDesc++
                if (asc + desc == 2020) {
                    println("Result is ${asc * desc}")
                    println("Counts are ${countAsc} and ${countDesc}")
                    return
                }
                if (asc + desc > 2020) return@desc
            }
        }
    }

    private fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toList() }

}