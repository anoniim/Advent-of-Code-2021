fun main() = Day().main()

private class Day {

    private val className = this::class.simpleName

    fun main() {
        // test if implementation meets criteria from the description:
        val testInput = parseInput("$className/${className}_test")
        check(part1(testInput) == 0)
        check(part2(testInput) == 0)

        val input = parseInput("$className/$className")
        println(part1(input))
        println(part2(input))
    }

    fun part1(input: List<Int>): Int {
        //
        return 0
    }

    fun part2(input: List<Int>): Int {
        //
        return 0
    }

    private fun parseInput(inputFile: String) = readInput(inputFile)[0].split(',').map { it.toInt() }
}