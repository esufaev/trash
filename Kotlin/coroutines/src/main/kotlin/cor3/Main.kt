package cor3

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlin.system.measureTimeMillis

suspend fun main() = coroutineScope<Unit> {

    val t1 = measureTimeMillis {
        delay(2000)
    }
    println("t1=$t1\n")

    val t2 = measureTimeMillis {
        delay(500)
    }
    println("t2=$t2\n")

    val t3 = measureTimeMillis {
        delay(50)
    }
    println("t3=$t3\n")

    val t4 = measureTimeMillis {
        delay(1000)
    }
    println("t4=$t4\n")

    println("Exit")
}