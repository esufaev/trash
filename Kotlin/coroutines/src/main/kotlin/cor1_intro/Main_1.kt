package cor1_intro

import kotlinx.coroutines.*

suspend fun main() = coroutineScope<Unit> {
    printHello()
    println("Program has finished")
}

suspend fun printHello() {
    delay(1000L)
    println("Hello cor!")
}