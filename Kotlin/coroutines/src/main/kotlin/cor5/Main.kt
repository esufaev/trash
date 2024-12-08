package cor5

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.properties.Delegates
import kotlin.system.measureTimeMillis

var initialTime by Delegates.notNull<Long>()

fun main() = println(
    measureTimeMillis {
        runBlocking {
            initialTime = System.currentTimeMillis()
            launch {
                delay(2200)
                println("launch 0 -->${delta()}")
                delay(2000)
                println("launch 1 -->${delta()}")
                launch {
                    delay(20)
                    println("inner launch 0 -->${delta()}")
                    delay(2000)
                    println("inner launch 1 -->${delta()}")
                }
                delay(200)
                println("launch 2 -->${delta()}")
            }

            delay(1500)
            println("fun main 0 -->${delta()}")
            delay(2000)
            println("fun main 1 -->${delta()}")
            delay(1000)
            println("fun main 2 -->${delta()}")
            delay(2)
            println("fun main 3 -->${delta()}")
        }
    }
)

fun delta():Long {
    return System.currentTimeMillis()-initialTime
}

