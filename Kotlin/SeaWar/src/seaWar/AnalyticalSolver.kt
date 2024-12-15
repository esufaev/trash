package seaWar

class AnalyticalSolver : SeaWarSolver {
    override val name: String = "Аналитик"

    private val moves = mutableSetOf<Point>()
    private val hitStack = mutableListOf<Point>()
    private val directions = listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)
    private val shipSizes = mutableListOf(4, 3, 3, 2, 2, 2, 1, 1, 1, 1)

    override fun makeMove(): Point {
        if (hitStack.isNotEmpty()) {
            val lastHit = hitStack.last()
            for (direction in directions) {
                val nextMove = Point(
                    x = lastHit.x + direction.first,
                    y = lastHit.y + direction.second
                )
                if (isValidMove(nextMove)) {
                    moves.add(nextMove)
                    return nextMove
                }
            }
            hitStack.removeAt(hitStack.size - 1)
        }

        return findMostProbableMove()
    }

    override fun registerHit(point: Point, isHit: Boolean) {
        if (isHit) {
            hitStack.add(point)
            reduceShipSize(point)
        }
    }

    private fun isValidMove(move: Point): Boolean {
        val (x, y) = move
        return x in 0..9 && y in 0..9 && move !in moves
    }

    private fun findMostProbableMove(): Point {
        val probabilityMap = Array(10) { IntArray(10) }

        for (shipSize in shipSizes) {
            for (x in 0..9) {
                for (y in 0..9) {
                    if (canPlaceShip(x, y, shipSize, horizontal = true)) {
                        for (i in 0 until shipSize) {
                            probabilityMap[x][y + i]++
                        }
                    }
                    if (canPlaceShip(x, y, shipSize, horizontal = false)) {
                        for (i in 0 until shipSize) {
                            probabilityMap[x + i][y]++
                        }
                    }
                }
            }
        }

        var maxProbability = 0
        var bestMove = Point(0, 0)
        for (x in 0..9) {
            for (y in 0..9) {
                if (probabilityMap[x][y] > maxProbability && Point(x, y) !in moves) {
                    maxProbability = probabilityMap[x][y]
                    bestMove = Point(x, y)
                }
            }
        }

        moves.add(bestMove)
        return bestMove
    }

    private fun canPlaceShip(x: Int, y: Int, size: Int, horizontal: Boolean): Boolean {
        for (i in 0 until size) {
            val checkX = if (horizontal) x else x + i
            val checkY = if (horizontal) y + i else y
            if (checkX !in 0..9 || checkY !in 0..9 || Point(checkX, checkY) in moves) {
                return false
            }
        }
        return true
    }

    private fun reduceShipSize(hitPoint: Point) {
        val hitSize = directions.count { dir ->
            val neighbor = Point(hitPoint.x + dir.first, hitPoint.y + dir.second)
            neighbor in hitStack
        } + 1
        shipSizes.removeIf { it == hitSize }
    }
}
