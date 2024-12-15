package cor6_2

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

suspend fun main() = coroutineScope<Unit> {
    val v1: Deferred<String> = async{ retMessage("1") }
    val v2: Deferred<String> = async{ retMessage("2") }
    val v3: Deferred<String> = async{ retMessage("3") }
    val msg1 = v1.await()
    println("received first")
    val msg2 = v2.await()
    println("received second")
    val msg3 = v3.await()
    println("received third")
    println("exit")
}

suspend fun retMessage(msg: String): String {
    println("${msg} sent")
    delay(1500)

    return msg
}