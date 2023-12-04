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
        day4.Part1()
    )
    SolutionRunner(solutions).letsGo()
}