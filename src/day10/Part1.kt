package day10

import solution.Dataset
import solution.Solution
import java.io.File

class Part1 : Solution {
    override val datasets = setOf(
        Dataset("src/day10/data/test_input_part1.txt", "8"),
        Dataset("src/day10/data/input.txt", "6927")
    )

    private val opposites = mapOf(
        "up" to "down",
        "down" to "up",
        "left" to "right",
        "right" to "left"
    )

    private val dirToPipe = mapOf(
        "up" to setOf("|", "7", "F"),
        "down" to setOf("|", "L", "J"),
        "right" to setOf("-", "J", "7"),
        "left" to setOf("-", "L", "F")
    )

    private val pipeToDir = mapOf(
        "|" to setOf("up", "down"),
        "-" to setOf("left", "right"),
        "L" to setOf("up", "right"),
        "J" to setOf("up", "left"),
        "7" to setOf("down", "left"),
        "F" to setOf("down", "right"),
    )

    private val grid = mutableListOf<MutableList<String>>()

    override fun getTitle(): String {
        return "Day 10: Pipe Maze - Part 1"
    }

    override fun getResult(dataset: Dataset): String {
        this.grid.clear()
        var sCoords = Pair(-1, -1)

        for ((i, line) in File(dataset.filepath).readLines().withIndex()) {
            val chars = mutableListOf<String>()
            for ((j, char) in line.withIndex()) {
                chars.add(char.toString())
                if (char.toString() == "S") {
                    sCoords = Pair(i, j)
                }
            }
            this.grid.add(chars)
        }

        possibleStart@for ((pipe, dirs) in this.pipeToDir) {
            if (dirs.count { canTravel(sCoords, it) } != 2) {
                continue
            }
            this.grid[sCoords.first][sCoords.second] = pipe

            var current = sCoords
            var direction = dirs.first()
            var stepCount = 0

            do {
                if (!canTravel(current, direction)) {
                    continue@possibleStart
                }
                current = getNext(current, direction)
                stepCount++
                direction = this.pipeToDir[char(current)]!!.filter { it != this.opposites[direction] }.first
            } while (current != sCoords)

            return (stepCount / 2).toString()
        }

        throw PipeException("No loop found")
    }

    private fun canTravel(coords: Pair<Int, Int>, direction: String): Boolean {
        return try {
            this.dirToPipe[direction]!!.contains(char(getNext(coords, direction)))
        } catch (e: PipeException) {
            false
        }
    }

    private fun char(coords: Pair<Int, Int>): String {
        return this.grid[coords.first][coords.second]
    }

    private fun getNext(coords: Pair<Int, Int>, direction: String): Pair<Int, Int> {
        val next = when (direction) {
            "down" -> Pair(coords.first + 1, coords.second)
            "up" -> Pair(coords.first - 1, coords.second)
            "left" -> Pair(coords.first, coords.second - 1)
            "right" -> Pair(coords.first, coords.second + 1)
            else -> throw PipeException("invalid direction")
        }

        if (next.first < 0 || next.first >= this.grid.count() || next.second < 0 || next.second >= this.grid.first.count()) {
            throw PipeException("out of bounds")
        }

        return next
    }
}

class PipeException(s: String) : Exception(s)