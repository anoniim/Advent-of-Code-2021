package day02

import readInput

fun main() { Day02part1().main() }

private class Day02part1 {

    fun main() {
        fun part1(commands: List<Command>): Int {
            val position = Position()
            commands.forEach {
                position += it.positionDelta()
            }
            return position.x * position.depth
        }

        // test if implementation meets criteria from the description, like:
        val testInput = parseInput("Day02_test")
        check(part1(testInput) == 150)

        val input = parseInput("Day02")
        println(part1(input))
    }

    private fun parseInput(inputFile: String) = readInput(inputFile).map { Command.parse(it) }

    private enum class Direction(val xChange: Int, val depthChange: Int) {
        UP(0, -1),
        DOWN(0, 1),
        FORWARD(1, 0);

        fun factor(): Position {
            return Position(xChange, depthChange)
        }
    }

    private class Command(val direction: Direction, val delta: Int) {

        fun positionDelta(): Position {
            val directionFactor = direction.factor()
            return Position(directionFactor.x * delta, directionFactor.depth * delta)
        }

        companion object {
            fun parse(commandString: String): Command {
                val (directionString, deltaString) = commandString.split(" ")
                val direction = Direction.valueOf(directionString.uppercase())
                val delta = deltaString.toInt()
                return Command(direction, delta)
            }
        }
    }

    private class Position(
        var x: Int = 0,
        var depth: Int = 0
    ) {
        operator fun plusAssign(change: Position) {
            x += change.x
            depth += change.depth
        }
    }
}