package day03

import readInput

fun main() {
    Day03().main()
}

private class Day03 {

    private val className = this::class.simpleName

    fun main() {
        fun part1(input: List<String>): Int {
            val gammaRate = calculateGammaRate(input)
            val epsilonRate = calculateEpsilonRate(gammaRate)
            return gammaRate.toInt(2) * epsilonRate.toInt(2)
        }

        fun part2(input: List<String>): Int {
            val oxygenRating = calculateOxygenRating(input)
            val co2ScrubberRating = calculateCo2ScrubberRating(input)
            return oxygenRating.toInt(2) * co2ScrubberRating.toInt(2)
        }

        // test if implementation meets criteria from the description, like:
        val testInput = parseInput("$className/${className}_test")
        check(part2(testInput) == 230)

        val input = parseInput("$className/$className")
        println(part1(input))
        println(part2(input))
    }

    private fun calculateOxygenRating(input: List<String>): String {
        return oxygenCriteria(input, 0)[0]
    }

    private fun oxygenCriteria(input: List<String>, index: Int): List<String> {
        if (input.size == 1) return input
        val mostCommonBit = input.mostCommonBitAt(index, "1")
        val filteredInput = input.filter { it[index].toString() == mostCommonBit }
        return oxygenCriteria(filteredInput, index + 1)
    }

    private fun calculateCo2ScrubberRating(input: List<String>): String {
        return co2ScrubberCriteria(input, 0)[0]
    }

    private fun co2ScrubberCriteria(input: List<String>, index: Int): List<String> {
        if (input.size == 1) return input
        val leastCommonBit = reverse(input.mostCommonBitAt(index, "1")[0])
        val filteredInput = input.filter { it[index].toString() == leastCommonBit }
        return co2ScrubberCriteria(filteredInput, index + 1)
    }

    private fun calculateGammaRate(input: List<String>): String {
        return buildString {
            for (i in input.first().indices) {
                append(input.mostCommonBitAt(i))
            }
        }
    }

    private fun List<String>.mostCommonBitAt(index: Int, valueIfEven: String = "0"): String {
        val zeroCount = count { it[index] == '0' }
        val halfListLength = size / 2
        return if (zeroCount == halfListLength) valueIfEven else if (zeroCount > halfListLength) "0" else "1"
    }

    private fun calculateEpsilonRate(input: String): String {
        return buildString {
            input.forEach { append(reverse(it)) }
        }
    }

    private fun reverse(bit: Char): String {
        return if (bit == '0') "1" else "0"
    }

    private fun parseInput(inputFile: String) = readInput(inputFile)
}