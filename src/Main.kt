import solution.SolutionRunner

fun main() {
    val solutions = setOf(
        day1.Part1(),
        day1.Part2(),
        day1.Part2Optimised(),
        day2.Part1(),
        day2.Part2()
    )
    SolutionRunner(solutions).letsGo()
}