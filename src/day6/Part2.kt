package day6

import solution.Dataset
import java.io.File

class Part2: Part1() {
    override val datasets = setOf(
        Dataset("src/day6/data/test_input_part1.txt", "71503"),
        Dataset("src/day6/data/input.txt", "45128024")
    )

    override fun getTitle(): String {
        return "Day 6: Wait For It - Part 2"
    }

    override fun getResult(dataset: Dataset): String {
        val file = File(dataset.filepath).readLines()
        val extract = { line: String -> line.substringAfter(":").replace(" ", "").toLong() }
        return this.getWinCount(extract(file[0]), extract(file[1]) + 1).toString()
    }
}