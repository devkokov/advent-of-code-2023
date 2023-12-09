package day9

import solution.Dataset

class Part2: Part1() {
    override val datasets = setOf(
        Dataset("src/day9/data/test_input_part1.txt", "2"),
        Dataset("src/day9/data/input.txt", "905")
    )

    override val forward = false

    override fun getTitle(): String {
        return "Day 9: Mirage Maintenance - Part 2"
    }
}