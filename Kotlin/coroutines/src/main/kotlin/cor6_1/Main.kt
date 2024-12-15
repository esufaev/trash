package cor6_1

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

suspend fun main() = coroutineScope<Unit> {
    for (i in 1..10) {
        runBlocking {
            launch {
                println("started task no. ${i} on thread ${Thread.currentThread().name}")
                delay(444)
                println("ended task no. ${i} on thread ${Thread.currentThread().name}")
            }
        }
    }
    println("\n\n")
    for (i in 1..10) {
        launch {
            println("started task no. ${i} on thread ${Thread.currentThread().name}")
            delay(444)
            println("ended task no. ${i} on thread ${Thread.currentThread().name}")
        }
    }
}