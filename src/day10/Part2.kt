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

        for (x in 0 until this.grid.count()) {
            for (y in 0 until this.grid[0].count()) {
                if (this.pipeCoords.containsKey("$x:$y")){
                    continue
                }

                val crossPipes = this.pipeCoords.filterValues { it.first.first == x || it.first.second == y }
                val minX = crossPipes.filterValues { it.first.second == y && it.first.first < x }.maxWithOrNull { a, b -> a.value.first.first.compareTo(b.value.first.first) }
                val maxX = crossPipes.filterValues { it.first.second == y && it.first.first > x }.minWithOrNull { a, b -> a.value.first.first.compareTo(b.value.first.first) }
                val minY = crossPipes.filterValues { it.first.first == x && it.first.second < y }.maxWithOrNull { a, b -> a.value.first.second.compareTo(b.value.first.second) }
                val maxY = crossPipes.filterValues { it.first.first == x && it.first.second > y }.minWithOrNull { a, b -> a.value.first.second.compareTo(b.value.first.second) }

                if (minX == null || maxX == null || minY == null || maxY == null) {
                    continue
                }

                val checkFactor = when (char(maxY.value.first)) {
                    "|" -> if (maxY.value.second == "up") -1 else 1
                    "L" -> if (maxY.value.second == "left") -1 else 1
                    "F" -> if (maxY.value.second == "up") -1 else 1
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