package cor6_2_dispatchers

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main() = coroutineScope {

    println("main на потоке: ${Thread.currentThread().name}")
    launch(Dispatchers.Default) {   // если не указвать, то выберется то же самое
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