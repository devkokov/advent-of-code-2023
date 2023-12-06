package day6

import solution.Dataset
import solution.Solution
import java.io.File
import kotlin.math.*

class Part1: Solution {
    override val datasets = setOf(
        Dataset("src/day6/data/test_input_part1.txt", "288"),
        Dataset("src/day6/data/input.txt", "800280")
    )

    override fun getTitle(): String {
        return "Day 6: Wait For It - Part 1"
    }

    override fun getResult(dataset: Dataset): String {
        val file = File(dataset.filepath).readLines()
        val extract = { line: String -> line.substringAfter(":").split(" ").mapNotNull { if (it.trim() == "") null else it.trim().toInt() } }
        val races = extract(file[0]).zip(extract(file[1]))
        return races.fold(1) { acc, race -> acc * this.getWinCount(race.first, race.second + 1) }.toString()
    }

    private fun getWinCount(time: Int, dist: Int): Int {
        val calc = { factor: Int -> (time + factor*sqrt(time.toDouble().pow(2) - 4*dist) )/2 }
        return (floor(calc(1)) - ceil(calc(-1)) + 1).toInt()
    }
}