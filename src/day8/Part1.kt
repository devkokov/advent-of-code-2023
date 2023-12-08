package day8

import solution.Dataset
import solution.Solution
import java.io.File

class Part1 : Solution {
    override val datasets = setOf(
        Dataset("src/day8/data/test_input_part1.txt", "2"),
        Dataset("src/day8/data/input.txt", "20659")
    )

    override fun getTitle(): String {
        return "Day 8: Haunted Wasteland - Part 1"
    }

    override fun getResult(dataset: Dataset): String {
        val file = File(dataset.filepath).readLines()
        val instructions = file.first
        val map = file.drop(2).associate { s ->
            s.substring(0, 3) to s.substringAfter("= ").split(",").map { s2 -> s2.filter { it.isLetter() } }
        }
        var step = 0
        var current = "AAA"
        while (current != "ZZZ") {
            current = when (instructions[step.mod(instructions.length)].toString()) {
                "L" -> map[current]!!.first
                "R" -> map[current]!!.last
                else -> throw Exception("Unknown instruction")
            }
            step++
        }
        return step.toString()
    }
}