package cor2

import kotlinx.coroutines.*

suspend fun main() = coroutineScope<Unit> {
    launch {
        printHello()
    }
    println("Program has finished")
}

suspend fun printHello() {
    delay(1000L)
    println("Hello cor!")
}