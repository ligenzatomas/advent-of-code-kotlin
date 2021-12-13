package twentyone

class Day12 {
    init {
        partOne()
    }

    fun partOne() {
        val graph = Graph()
        Utils()
            .parseLines("12")
            .toList()
            .forEach {
                val list = it.split('-')
                graph.addEdge(list[0], list[1])
            }

        graph.printAllPaths("start", "end")

        println(graph.results.size)
    }

    class Graph {
        val adjList: MutableMap<String, MutableList<String>> = mutableMapOf()
        val results: MutableList<MutableList<String>> = mutableListOf()

        fun addEdge(start: String, end: String) {
            if (!adjList.containsKey(start)) {
                adjList[start] = mutableListOf(end)
            } else {
                adjList[start]?.add(end)
            }

            if (!adjList.containsKey(end)) {
                adjList[end] = mutableListOf(start)
            } else {
                adjList[end]?.add(start)
            }
        }

        fun printAllPaths(start: String, end: String) {
            val isVisited = mutableMapOf<String, Boolean>()
            val pathList = mutableListOf<String>()

            pathList.add(start)

            printAllPathsUtil(start, end, isVisited, pathList)
        }

        fun printAllPathsUtil(u: String, d: String, isVisited: MutableMap<String, Boolean>, localPathList: MutableList<String>) {
            if (u == d) {
                results.add(localPathList)
                println(localPathList)
                return
            }

            if (u == "start") {
                isVisited[u] = true
            } else if (u.all { it.isLowerCase() } && localPathList.count { it == u } == 2) {
                isVisited[u] = true
            }

            for (s: String in adjList[u]!!.filter {
                !(it.all { c -> c.isLowerCase() } && localPathList.contains(it) && isVisited.any { entry -> entry.key.all { c ->  c.isLowerCase() } && entry.value && entry.key != "start" })
            }) {
                if (!isVisited.containsKey(s) || (isVisited.containsKey(s) && isVisited[s] == false)) {
                    localPathList.add(s)
                    printAllPathsUtil(s, d, isVisited, localPathList)

                    localPathList.remove(s)
                }
            }

            isVisited[u] = false
        }
    }
}