package day3

import solution.Dataset
import solution.Solution
import java.io.File

class Part2: Solution {
    override val datasets = setOf(
        Dataset("src/day3/data/test_input_part1.txt", "467835"),
        Dataset("src/day3/data/input.txt", "72553319")
    )

    override fun getTitle(): String {
        return "Day 3: Gear Ratios - Part 2"
    }

    var gears = mutableMapOf<String, MutableList<Int>>()
    var grid = emptyList<CharArray>()

    override fun getResult(dataset: Dataset): String {
        grid = File(dataset.filepath).readLines().map { it.toCharArray() }
        gears = mutableMapOf()

        for ((x, line) in grid.withIndex()) {
            var buffer = ""
            var numY: Int? = null

            for ((y, char) in line.withIndex()) {
                if (char.isDigit()) {
                    buffer += char.toString()
                    numY = numY ?: y
                    if (y != line.lastIndex) {
                        continue
                    }
                }
                if (buffer == "") {
                    continue
                }
                populateGearsMapForNum(buffer, x, numY!!)
                buffer = ""
                numY = null
            }
        }

        return gears.filterValues { it.count() == 2 }.values.sumOf { it.reduce {ratio, value -> ratio * value} }.toString()
    }

    private fun populateGearsMapForNum(num: String, numX: Int, numY: Int) {
        val isGear = { x: Int, y: Int ->
            try {
                grid[x][y].toString() == "*"
            } catch (e: IndexOutOfBoundsException) {
                false
            }
        }

        val coordsToCheck = mutableListOf(
            Pair(numX, numY - 1),
            Pair(numX, numY + num.length),
        )
        (-1..num.length).map { coordsToCheck.addAll(listOf(Pair(numX - 1, numY + it), Pair(numX + 1, numY + it))) }

        for (gear in coordsToCheck.filter { isGear(it.first, it.second) }) {
            val id = "${gear.first}:${gear.second}"
            if (gears.containsKey(id)) {
                gears[id]!!.add(num.toInt())
            } else {
                gears[id] = mutableListOf(num.toInt())
            }
        }
    }
}