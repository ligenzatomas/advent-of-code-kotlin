package twentythree

class Day03 {

    init {
        firstPart()
        secondPart()
    }

    // 577212 too high
    // 506273 too low
    // 507214 right
    private fun firstPart() {
        val symbols = parseSymbols(Utils().parseLines("03"))
        val numbers = parseNumbers(Utils().parseLines("03"))

        val result = numbers.map {
            NumberResult(it.line, it.positionStart, it.positionEnd, it.value,
                    symbols.any { symbol ->
                (symbol.line == it.line && (symbol.position == it.positionStart-1 || symbol.position == it.positionEnd+1))
                        || (symbol.line == it.line-1 && (symbol.position >= it.positionStart-1 && symbol.position <= it.positionEnd+1))
                        || (symbol.line == it.line+1 && (symbol.position >= it.positionStart-1 && symbol.position <= it.positionEnd+1))
            })
        }

        println(result.filter { it.hasSymbol }.sumOf { it.value })
    }

    // 9215 too low
    // 238966 too low
    // 57487951 too low
    // 72553319 right
    private fun secondPart() {
        val symbols = parseSymbols(Utils().parseLines("03"))
        val numbers = parseNumbers(Utils().parseLines("03"))

        val result = symbols
            //.filter { it.line == 4 && it.position == 32 }
            .filter { it.value == '*' }
            .map {
                var adjs = numbers
                    //.filter { number -> number.value == 906 || number.value == 634 }
                    .filter { number ->
                    (number.line == it.line && (number.positionStart-1 <= it.position && number.positionEnd+1 >= it.position))
                            || (number.line == it.line-1 && (number.positionStart-1 <= it.position && number.positionEnd+1 >= it.position))
                            || (number.line == it.line+1 && (number.positionStart-1 <= it.position && number.positionEnd+1 >= it.position))
                }

                if (adjs.size == 2) {
                    SymbolMultiply(it.line, it.position, it.value, adjs[0].value * adjs[1].value)
                } else {
                    SymbolMultiply(it.line, it.position, it.value, 0)
                }
            }

        println(result.sumOf { it.multiply  })
    }

    private fun parseSymbols(inputs: List<String>): List<Symbol> {
        return inputs.mapIndexed { index, line ->
            line.mapIndexed { indexChar, char ->
                if (isCharSymbol(char)) {
                    Symbol(index, indexChar, char)
                } else {
                    null
                }
            }
        }
            .flatten()
            .filterNotNull()
            .sortedBy { it.line }
    }

    private fun parseNumbers(inputs: List<String>): List<Number> {
        return inputs.mapIndexed { index, line ->
            var number = ""
            var positionStart = 0
            var positionEnd = 0
            var result = mutableListOf<Number>()

            line.forEachIndexed { indexLine, c ->
                if (isCharNumber(c)) {
                    if (number.isEmpty()) {
                        positionStart = indexLine
                    }

                    number += c
                } else {
                    if (number.isNotEmpty()) {
                        positionEnd = indexLine-1
                        result.add(Number(index, positionStart, positionEnd, number.toInt()))
                        number = ""
                    }
                }
            }

            if (number.isNotEmpty()) {
                positionEnd = line.length-1
                result.add(Number(index, positionStart, positionEnd, number.toInt()))
            }

            result
        }
            .flatten()
    }


    private fun isCharSymbol(char: Char): Boolean {
        return !char.isDigit() && char != '.'
    }

    private fun isCharNumber(char: Char): Boolean {
        return char.isDigit()
    }

    data class Symbol(val line: Int, val position: Int, val value: Char)
    data class Number(val line: Int, val positionStart: Int, val positionEnd: Int, val value: Int)

    data class NumberResult(val line: Int, val positionStart: Int, val positionEnd: Int, val value: Int, val hasSymbol: Boolean)

    data class SymbolMultiply(val line: Int, val position: Int, val value: Char, val multiply: Int)
}