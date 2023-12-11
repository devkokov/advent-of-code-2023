package day11

import solution.Dataset
import solution.Solution
import java.io.File
import kotlin.math.absoluteValue

class Part1 : Solution {
    override val datasets = setOf(
        Dataset("src/day11/data/test_input_part1.txt", "374"),
        Dataset("src/day11/data/input.txt", "9274989")
    )

    override fun getTitle(): String {
        return "Day 11: Cosmic Expansion - Part 1"
    }

    override fun getResult(dataset: Dataset): String {
        var rows = 0
        var cols = 0

        val galaxies = mutableListOf<Pair<Int, Int>>()
        val galaxyRows = mutableSetOf<Int>()
        val galaxyCols = mutableSetOf<Int>()

        for ((row, line) in File(dataset.filepath).readLines().withIndex()) {
            rows++
            cols = 0
            for ((col, char) in line.withIndex()) {
                cols++
                if (char.toString() != "#") {
                    continue
                }
                galaxies.add(Pair(row, col))
                galaxyRows.add(row)
                galaxyCols.add(col)
            }
        }

        val incrementRowsBy = mutableMapOf<Int, Int>()
        val incrementColsBy = mutableMapOf<Int, Int>()

        var incrementRowBy = 0
        for (row in 0 until rows) {
            if (!galaxyRows.contains(row)) {
                incrementRowBy++
            }
            incrementRowsBy[row] = incrementRowBy
        }

        var incrementColBy = 0
        for (col in 0 until cols) {
            if (!galaxyCols.contains(col)) {
                incrementColBy++
            }
            incrementColsBy[col] = incrementColBy
        }

        val expandedGalaxies = galaxies.map { Pair(it.first + incrementRowsBy[it.first]!!, it.second + incrementColsBy[it.second]!!) }

        var sum = 0
        for ((i, galaxyA) in expandedGalaxies.withIndex()) {
            for (j in i+1 until expandedGalaxies.count()) {
                val galaxyB = expandedGalaxies[j]
                sum += (galaxyA.first - galaxyB.first).absoluteValue + (galaxyA.second - galaxyB.second).absoluteValue
            }
        }

        return sum.toString()
    }
}