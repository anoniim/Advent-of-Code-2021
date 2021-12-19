package day04

import readInput

fun main() {
    Day04().main()
}

private class Day04 {

    private val className = this::class.simpleName

    fun main() {
        fun part1(input: List<String>): Int {
            // Win over the squid
            val numbers = input[0].split(",").map { it.toInt() }
            val boards = parseBoards(input.subList(2, input.size))
            var currentNumberIndex = 0
            var score = 0
            while (score == 0) {
                val currentNumber = numbers[currentNumberIndex]
                run boardCheck@{
                    boards.forEach {
                        it.mark(currentNumber)
                        score = it.check() * currentNumber
                        if (score > 0) return@boardCheck
                    }
                    currentNumberIndex++
                }
            }
            return score
        }

        fun part2(input: List<String>): Int {
            // Let the squid win
            val numbers = input[0].split(",").map { it.toInt() }
            val boards = parseBoards(input.subList(2, input.size))
            val boardsThatHaventWon = boards.toMutableList()
            for (currentNumberIndex in numbers.indices) {
                val currentNumber = numbers[currentNumberIndex]
                boards.forEach {
                    it.mark(currentNumber)
                    val score: Int = it.check() * currentNumber
                    if (score > 0) boardsThatHaventWon.remove(it)
                    if (boardsThatHaventWon.size == 0) {
                        return score
                    }
                }
            }
            return 0
        }

        // test if implementation meets criteria from the description, like:
        val testInput = parseInput("$className/${className}_test")
        check(part2(testInput) == 1924)

        val input = parseInput("$className/$className")
        println(part1(input))
        println(part2(input))
    }

    private fun parseBoards(input: List<String>): List<Board> {
        return input.windowed(5, 6, false).map {
            Board.parse(it)
        }
    }

    private class Board(val grid: Array<IntArray>, val size: Int = 5) {

        var hasWon = false
        val markedNumbers = Array(size) { BooleanArray(size) { false } }

        fun mark(number: Int) {
            for (column in 0 until size) {
                for (row in 0 until size) {
                    if (grid[row][column] == number) markedNumbers[row][column] = true
                }
            }
        }

        fun check(): Int {
            hasWon = checkRows() || checkCols()
            return if (hasWon) calculateScore() else 0
        }

        private fun calculateScore(): Int {
            var score = 0
            for (column in 0 until size) {
                for (row in 0 until size) {
                    if (!markedNumbers[row][column]) score += grid[row][column]
                }
            }
            return score
        }

        private fun checkCols(): Boolean {
            for (column in 0 until size) {
                var hasWon = true
                for (row in 0 until size) {
                    hasWon = hasWon && markedNumbers[row][column]
                }
                if (hasWon) return true
            }
            return false
        }

        private fun checkRows(): Boolean {
            return markedNumbers.any { row -> row.all { it } }
        }

        companion object {
            fun parse(input: List<String>): Board {
                val size = 5
                val grid = Array(size) { lineIndex ->
                    input[lineIndex].windowed(2, 3, false).map { it.trim().toInt() }.toIntArray()
                }
                return Board(grid, size)
            }
        }
    }

    private fun parseInput(inputFile: String) = readInput(inputFile)
}