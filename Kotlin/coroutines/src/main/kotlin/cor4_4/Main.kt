package cor4_4

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

suspend fun main() = coroutineScope<Unit> {

    launch {
        val t1 = measureTimeMillis {
            delay(20)
        }
        println("t1=$t1\n")
    }

    launch {
        val t2 = measureTimeMillis {
            delay(50)
        }
        println("t2=$t2\n")
    }

    launch {
        val t3 = measureTimeMillis {
            delay(60)
        }
        println("t3=$t3\n")
    }

    launch {
        val t4 = measureTimeMillis {
            delay(0)
        }
        println("t4=$t4\n")
    }

    println("Exit")
}