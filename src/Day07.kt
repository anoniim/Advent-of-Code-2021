import kotlin.math.abs

fun main() = Day07().main()

private class Day07 {

    fun main() {
        // test if implementation meets criteria from the description:
        val testInput = parseInput("${this::class.simpleName}_test")
        check(part1(testInput) == 37)
        check(part2(testInput) == 168)

        val input = parseInput("${this::class.simpleName}")
        println(part1(input))
        println(part2(input))
    }

    fun part1(input: List<Int>): Int {
        // What's the cheapest position and how much fuel do crabs spend to align to that position?
        // TODO Order and find median, then test values from there
        val minPosition = input.minOf { it }
        val maxPosition = input.maxOf { it }
        var minCost = Int.MAX_VALUE
        for (position in minPosition..maxPosition) {
            val cost = calculateCostPart1(position, input)
            if (cost < minCost) minCost = cost
        }
        return minCost
    }

    private fun calculateCostPart1(target: Int, input: List<Int>): Int {
        var cost = 0
        for (position in input) {
            cost += abs(target - position)
        }
        return cost
    }

    fun part2(input: List<Int>): Int {
        // Updated cost algorithm
        val minPosition = input.minOf { it }
        val maxPosition = input.maxOf { it }
        var minCost = Int.MAX_VALUE
        for (position in minPosition..maxPosition) {
            val cost = calculateCostPart2(position, input)
            if (cost < minCost) minCost = cost
        }
        return minCost
    }

    private fun calculateCostPart2(target: Int, input: List<Int>): Int {
        var cost = 0
        for (position in input) {
            val distance = abs(target - position)
            cost += calculateCostForDistance(distance)
        }
        return cost
    }

    private fun calculateCostForDistance(distance: Int): Int {
        var cost = 0
        for (i in distance downTo 0) {
            cost += i
        }
        return cost
    }

    private fun parseInput(inputFile: String) = readInput(inputFile)[0].split(',').map { it.toInt() }
}