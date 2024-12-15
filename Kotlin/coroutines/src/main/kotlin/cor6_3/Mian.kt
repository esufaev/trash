package cor6_3

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main() = coroutineScope {

    println("main на потоке: ${Thread.currentThread().name}")
    launch(Dispatchers.Default) {
        println("Корутина 1 на потоке до: ${Thread.currentThread().name}")
        delay(100)
        println("Корутина 1 на потоке после: ${Thread.currentThread().name}")
    }
    launch(Dispatchers.Unconfined) {
        println("Корутина 2 на потоке до: ${Thread.currentThread().name}")
        delay(500)
        println("Корутина 2 на потоке после: ${Thread.currentThread().name}")
    }

    println("exit")
}