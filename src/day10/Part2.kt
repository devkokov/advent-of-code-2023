package day10

import solution.Dataset

class Part2 : Part1() {
    override val datasets = setOf(
        Dataset("src/day10/data/test_input_part2-0.txt", "4"),
        Dataset("src/day10/data/test_input_part2-1.txt", "8"),
        Dataset("src/day10/data/test_input_part2-2.txt", "10"),
        Dataset("src/day10/data/input.txt", "467")
    )

    override fun getTitle(): String {
        return "Day 10: Pipe Maze - Part 2"
    }

    override fun getResult(dataset: Dataset): String {
        this.processGrid(dataset)

        var area = 0
        val directionFactor = if (loopDirection > 0) 1 else -1

        val pipesByX = mutableMapOf<Int, MutableList<Pair<Pair<Int, Int>, String>>>()
        val pipesByY = mutableMapOf<Int, MutableList<Pair<Pair<Int, Int>, String>>>()

        for (pipe in this.pipeCoords) {
            if (!pipesByX.containsKey(pipe.value.first.first)) {
                pipesByX[pipe.value.first.first] = mutableListOf()
            }
            if (!pipesByY.containsKey(pipe.value.first.second)) {
                pipesByY[pipe.value.first.second] = mutableListOf()
            }
            pipesByX[pipe.value.first.first]!!.add(pipe.value)
            pipesByY[pipe.value.first.second]!!.add(pipe.value)
        }

        for (x in 0 until this.grid.count()) {
            for (y in 0 until this.grid[0].count()) {
                if (this.pipeCoords.containsKey("$x:$y") || !pipesByX.containsKey(x) || !pipesByY.containsKey(y)) {
                    continue
                }

                val down = pipesByY[y]!!.filter { it.first.first < x }
                val up = pipesByY[y]!!.filter { it.first.first > x }
                val left = pipesByX[x]!!.filter { it.first.second < y }
                val right = pipesByX[x]!!.filter { it.first.second > y }

                if (down.isEmpty() || up.isEmpty() || left.isEmpty() || right.isEmpty()) {
                    continue
                }

                val rightBound = right.minWith { a, b -> a.first.second.compareTo(b.first.second) }

                val checkFactor = when (char(rightBound.first)) {
                    "|" -> if (rightBound.second == "up") -1 else 1
                    "L" -> if (rightBound.second == "left") -1 else 1
                    "F" -> if (rightBound.second == "up") -1 else 1
                    else -> 0
                }

                if (checkFactor * directionFactor != 1) {
                    continue
                }

                area++
            }
        }

        return area.toString()
    }
}