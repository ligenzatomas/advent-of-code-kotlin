package twenty

class Day14 {
    constructor() {
        firstPart()
    }

    lateinit var mask: CharArray
    val memory = mutableMapOf<Int, String>()

    // 266695120 to low
    fun firstPart() {
        val inputs = Utils().parseLines("14")

        inputs.forEach {
            if ("^mask = ([X10]*)$".toRegex().matches(it)) {
                initMask("^mask = ([X10]*)$".toRegex().find(it)!!.groupValues[1]);
            }

            if ("^mem\\[(\\d*)\\] = (\\d*)$".toRegex().matches(it)) {
                val result = "^mem\\[(\\d*)\\] = (\\d*)$".toRegex().find(it)!!
                setMemory(result.groupValues[1].toInt(), result.groupValues[2].toInt())
            }
        }

        println(memory)

        val mem = memory.map { convertBinaryToDecimal(it.value) }.toList()

        println(mem)
        println(mem.sum())
    }

    fun initMask(str: String) {
        mask = str.toCharArray()
        println(mask)
    }

    fun addMaskToBinary(binary: String): String {
        var result = ""
        mask.reversed().forEachIndexed { index, maskChar ->
            if (binary.reversed().length > index) {
                if (maskChar == 'X') {
                    result += binary.reversed()[index]
                } else {
                    result += maskChar
                }
            } else {
                if (maskChar == 'X') {
                    result += '0'
                } else {
                    result += maskChar
                }
            }
        }

        println(binary + " " + result.reversed())

        return result.reversed()
    }

    fun setMemory(index: Int, value: Int) {
        println("setMemory " + index + " " + value + " " + convertDecimalToBinary(value))
        memory.put(
            index,
            addMaskToBinary(convertDecimalToBinary(value))
        )
    }

    fun convertDecimalToBinary(num: Int): String {
        return Integer.toBinaryString(num)
    }

    fun convertBinaryToDecimal(binary: String): Int {
        val numbers: CharArray = binary.toCharArray()
        var result = 0
        for (i in numbers.indices.reversed()) if (numbers[i] == '1') result += Math.pow(2.0, (numbers.size - i - 1).toDouble()).toInt()
        return result
    }
}