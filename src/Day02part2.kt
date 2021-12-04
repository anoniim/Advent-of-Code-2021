fun main() {
    Day02part2().main()
}

private class Day02part2 {
    fun main() {
        fun part2(input: List<Movement>): Int {
            val position = Position()
            input.forEach { newPosition ->
                position += newPosition.positionDelta(position.aim)
            }
            return position.x * position.depth
        }

        // test if implementation meets criteria from the description, like:
        val testInput = parseInput("Day02_test")
        check(part2(testInput) == 900)

        val input = parseInput("Day02")
        println(part2(input))
    }

    private fun parseInput(inputFile: String) = readInput(inputFile).map { Movement.parse(it) }

    private enum class Direction(val xChange: Int, val aimChange: Int) {
        UP(0, -1),
        DOWN(0, 1),
        FORWARD(1, 0);

        fun factor(): Position {
            return Position(xChange, 0, aimChange)
        }
    }

    private class Movement(val direction: Direction, val delta: Int) {

        fun positionDelta(aim: Int): Position {
            val directionFactor = direction.factor()
            val forwardMovement = directionFactor.x * delta
            return Position(forwardMovement, forwardMovement * aim, directionFactor.aim * delta)
        }

        companion object {
            fun parse(input: String): Movement {
                val split = input.split(" ")
                val direction = Direction.valueOf(split[0].uppercase())
                return Movement(direction, split[1].toInt())
            }
        }
    }

    private class Position(
        var x: Int = 0,
        var depth: Int = 0,
        var aim: Int = 0
    ) {
        operator fun plusAssign(change: Position) {
            x += change.x
            depth += change.depth
            aim += change.aim
        }
    }
}
