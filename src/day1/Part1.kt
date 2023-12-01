package day1

import Solution
import java.io.File

class Part1: Solution {
    override fun getTitle(): String {
        return "Day 1: Trebuchet?! - Part 1"
    }

    override fun run() {
        val example = "1abc2\n" +
                "pqr3stu8vwx\n" +
                "a1b2c3d4e5f\n" +
                "treb7uchet"

        println("Running against example dataset")

        var result = getResult(example)

        print("Result $result - ")

        if (result == 142) {
            println("OK")
        } else {
            println("FAIL")
            return
        }

        println("Running against large dataset")

        result = getResult(File("src/day1/input.txt").readText())
        println("Result $result")
    }

    private fun getResult(input: String): Int {
        var sum = 0
        for (line in input.split("\n")) {
            val digits = line.filter { it.isDigit() }
            val num = digits.first().toString() + digits.last().toString()
            sum += num.toInt()
        }
        return sum
    }
}