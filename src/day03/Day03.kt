package day03

import readInput

fun main() {
    Day03().main()
}

private class Day03 {

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
        val testInput = parseInput("${this::class.simpleName}_test")
        check(part2(testInput) == 230)

        val input = parseInput("${this::class.simpleName}")
        println(part1(input))
        println(part2(input))
    }

    private fun calculateOxygenRating(input: List<String>): String {
        return oxygenCriteria(input, 0)[0]
    }

    private fun oxygenCriteria(input: List<String>, index: Int): List<String> {
        if (input.size == 1) return input
        val mostCommonBit = mostCommonBit(input, index, 1)
        val filteredInput = input.filter { it[index].toString() == mostCommonBit }
        return oxygenCriteria(filteredInput, index + 1)
    }

    private fun calculateCo2ScrubberRating(input: List<String>): String {
        return co2ScrubberCriteria(input, 0)[0]
    }

    private fun co2ScrubberCriteria(input: List<String>, index: Int): List<String> {
        if (input.size == 1) return input
        val leastCommonBit = reverse(mostCommonBit(input, index, 1)[0])
        val filteredInput = input.filter { it[index].toString() == leastCommonBit }
        return co2ScrubberCriteria(filteredInput, index + 1)
    }

    private fun calculateGammaRate(input: List<String>): String {
        var result = ""
        for (i in 0 until input[0].length) {
            val bit: String = mostCommonBit(input, i)
            result += bit
        }
        return result
    }

    private fun mostCommonBit(input: List<String>, index: Int, valueIfEven: Int = 0): String {
        var zeroCount = 0
        input.forEach {
            if (it[index].toString().toInt() == 0) zeroCount++
        }
        return if (zeroCount == input.size / 2) valueIfEven.toString() else if (zeroCount > input.size / 2) "0" else "1"
    }

    private fun calculateEpsilonRate(input: String): String {
        var output = ""
        input.forEach { output += reverse(it) }
        return output
    }

    private fun reverse(bit: Char): String {
        return if (bit == '0') "1" else "0"
    }

    private fun parseInput(inputFile: String) = readInput(inputFile)
}