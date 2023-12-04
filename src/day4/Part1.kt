package day4

import solution.Dataset
import solution.Solution
import java.io.File

class Part1: Solution {
    override val datasets = setOf(
        Dataset("src/day4/data/test_input_part1.txt", "13"),
        Dataset("src/day4/data/input.txt", "21213")
    )

    override fun getTitle(): String {
        return "Day 4: Scratchcards - Part 1"
    }

    override fun getResult(dataset: Dataset): String {
        var sum = 0
        for (card in File(dataset.filepath).readLines()) {
            val nums = card.substringAfter(": ").split(" | ")
            val getSet = { s: String -> s.split(" ").map { it.trim() }.filter { it != "" }.toSet() }
            val winning = getSet(nums.first) intersect getSet(nums.last)
            sum += if (winning.isEmpty()) 0 else winning.fold(0.5) { i, _ -> i * 2 }.toInt()
        }
        return sum.toString()
    }
}