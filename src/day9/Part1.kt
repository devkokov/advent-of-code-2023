package day9

import solution.Dataset
import solution.Solution
import java.io.File

open class Part1 : Solution {
    override val datasets = setOf(
        Dataset("src/day9/data/test_input_part1.txt", "114"),
        Dataset("src/day9/data/input.txt", "1901217887")
    )

    protected open val forward = true

    override fun getTitle(): String {
        return "Day 9: Mirage Maintenance - Part 1"
    }

    override fun getResult(dataset: Dataset): String {
        var sum = 0.toLong()
        for (line in File(dataset.filepath).readLines()) {
            val nums = line.split(" ").map { it.toLong() }
            val diffs = traverseLists(nums)
            sum += if (this.forward) nums.last + diffs.last else nums.first - diffs.first
        }
        return sum.toString()
    }

    private fun traverseLists(nums: List<Long>): List<Long> {
        val diffs = mutableListOf<Long>()
        var allNils: Boolean? = null
        for (i in 0 until nums.count() - 1) {
            val diff = nums[i+1] - nums[i]
            diffs.add(i, diff)
            allNils = allNils != false && diff == 0.toLong()
        }
        if (allNils == true) {
            return diffs
        }
        val nextDiffs = traverseLists(diffs)
        if (this.forward) {
            diffs.add(diffs.last + nextDiffs.last)
        } else {
            diffs.add(0, diffs.first - nextDiffs.first)
        }
        return diffs
    }
}