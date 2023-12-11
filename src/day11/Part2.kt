package day11

import solution.Dataset

class Part2 : Part1() {
    override val datasets = setOf(
        Dataset("src/day11/data/input.txt", "357134560737")
    )

    override fun getTitle(): String {
        return "Day 11: Cosmic Expansion - Part 2"
    }

    override val expansionFactor = 1000000.toLong()
}