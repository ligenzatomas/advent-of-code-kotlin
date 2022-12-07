package twentytwo

import kotlin.math.abs

class Day07 {
    init {
        allParts()
    }

    private fun allParts() {
        val iterator = Utils().parseLines("07").iterator()
        val actualPath = mutableListOf<String>()
        val tree = Node(DirectoryOrFile("/", null, true))
        var inLs = false

        var sum = 0
        val needed = 10822529
        var closest = 0

        while (iterator.hasNext()) {
            val line = iterator.next()

            if (line == "$ cd /") {
                actualPath.clear()
                inLs = false
            }

            if (line == "$ cd ..") {
                // I added few "$ cd .." lines to the end of the input file
                if (actualPath.isEmpty()) {
                    break
                }

                val actual = find(actualPath, tree)
                actual.value.size = actual.children.sumOf {
                    it.value.size ?: 0
                }

                // first part
                if (actual.value.size!! <= 100000) {
                    sum += actual.value.size!!
                }

                // second part
                if (needed <= actual.value.size!! && abs(needed - actual.value.size!!) < abs(needed - closest)) {
                    closest = actual.value.size!!
                }

                actualPath.removeLast()
                inLs = false
            }

            if ("""\$ cd ([a-zA-Z]*)""".toRegex().matches(line)) {
                val directoryName = """\$ cd ([a-zA-Z]*)""".toRegex().find(line)?.groupValues!!

                actualPath.add(directoryName[1])
                inLs = false
            }

            if (line == "$ ls") {
                inLs = true
            } else if (inLs) {
                if ("dir ([a-zA-Z]*)".toRegex().matches(line)) {
                    val directoryName = "dir ([a-zA-Z]*)".toRegex().find(line)?.groupValues!!
                    find(actualPath, tree).addChild(Node(DirectoryOrFile(directoryName[1], null, true)))
                } else {
                    val groupValues = """(\d+) ([a-zA-Z]*)""".toRegex().find(line)?.groupValues!!
                    find(actualPath, tree).addChild(Node(DirectoryOrFile(groupValues[2], groupValues[1].toInt(), false)))
                }
            }
        }

        actualPath.clear()
        val actual = find(actualPath, tree)
        actual.value.size = actual.children.sumOf {
            it.value.size ?: 0
        }

        println("sum ${sum}")
        println("Free space " + (70000000 - actual.value.size!!))
        println("Free space needed " + (30000000 - (70000000 - actual.value.size!!)))
        // 10822529 is needed
        println("closest ${closest}")
    }

    private fun find(path: List<String>, treeRoot: Node): Node {
        var result = treeRoot
        path.forEach {
            if (result.children.any { child -> child.nameEquals(it) }) {
                result = result.children.first { child -> child.nameEquals(it) }
            } else {
                val new = Node(DirectoryOrFile(it, null, true))
                result.children.add(new)
                result = new
            }
        }
        return result
    }

    class Node(val value: DirectoryOrFile) {
        var parent: Node? = null
        var children: MutableList<Node> = mutableListOf()

        fun addChild(node: Node) {
            children.add(node)
            node.parent = this
        }

        fun hasChildren(): Boolean {
            return children.size >= 1
        }

        fun nameEquals(name: String): Boolean {
            return value.name == name
        }

        override fun toString(): String {
            return "Node(name=${value.name},size=${value.size})"
        }


    }

    data class DirectoryOrFile(val name: String, var size: Int?, val directory: Boolean) {

    }

    class File(val name: String, val size: Int) {

    }
}