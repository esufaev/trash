package cor1

import kotlinx.coroutines.*

suspend fun printHello() {
    delay(1000L)
    println("Hello cor!")
}
suspend fun main() = coroutineScope<Unit> {
    printHello()
    println("Program has finished")
}