import solution.SolutionRunner

fun main() {
    val solutions = setOf(
        day1.Part1(),
        day1.Part2(),
        day1.Part2Optimised(),
        day2.Part1(),
        day2.Part2(),
        day3.Part1(),
        day3.Part2(),
        day4.Part1(),
        day4.Part2(),
        day5.Part1(),
        day5.Part2(),
        day6.Part1(),
        day6.Part2(),
        day7.Part1(),
        day7.Part2(),
        day8.Part1(),
        day8.Part2(),
        day9.Part1(),
        day9.Part2(),
        day10.Part1(),
        day10.Part2(),
        day11.Part1(),
        day11.Part2(),
        day12.Part1(),
    )
    SolutionRunner(solutions).letsGo()
}