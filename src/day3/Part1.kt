package day3

import solution.Dataset
import solution.Solution
import java.io.File

class Part1: Solution {
    override val datasets = setOf(
        Dataset("src/day3/data/test_input_part1.txt", "4361"),
        Dataset("src/day3/data/input.txt", "507214")
    )

    override fun getTitle(): String {
        return "Day 3: Gear Ratios - Part 1"
    }

    override fun getResult(dataset: Dataset): String {
        val grid = File(dataset.filepath).readLines().map { it.toCharArray() }

        var sum = 0

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
                if (isPartNumber(buffer, x, numY!!, grid)) {
                    sum += buffer.toInt()
                }
                buffer = ""
                numY = null
            }
        }
        return sum.toString()
    }

    private fun isPartNumber(num: String, numX: Int, numY: Int, grid: List<CharArray>): Boolean {
        val isSymbol = { x: Int, y: Int ->
            try {
                !grid[x][y].isDigit() && grid[x][y].toString() != "."
            } catch (e: IndexOutOfBoundsException) {
                false
            }
        }
        if (isSymbol(numX, numY - 1) || isSymbol(numX, numY + num.length)) {
            return true
        }
        for (i in -1..num.length) {
            if (isSymbol(numX - 1, numY + i) || isSymbol(numX + 1, numY + i)) {
                return true
            }
        }
        return false
    }
}