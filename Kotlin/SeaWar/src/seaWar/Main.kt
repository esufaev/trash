package seaWar

fun main() {
    val randomVsAnalytical = mutableListOf<GameResult>()

    SeaBattleGame(
        firstPlayer = RandomSolver(),
        secondPlayer = AnalyticalSolver()
    ).start(showLogs = true)

    repeat(100) {
        SeaBattleGame(
            firstPlayer = RandomSolver(),
            secondPlayer = AnalyticalSolver()
        ).start(showLogs = false).also(randomVsAnalytical::add)
    }

    val randomWins = randomVsAnalytical.filter { it.winner is RandomSolver }
    val analyticalWins = randomVsAnalytical.filter { it.winner is AnalyticalSolver }

    println("\nРезультаты 100 игр")
    println("Аналитик победил: ${analyticalWins.size}, среднее число ходов: ${if (analyticalWins.isNotEmpty()) analyticalWins.sumOf { it.turns } / analyticalWins.size else 0}")
    println("Случайный победил: ${randomWins.size}, среднее число ходов: ${if (randomWins.isNotEmpty()) randomWins.sumOf { it.turns } / randomWins.size else 0}")
}
