package erm.sandbox

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.system.measureTimeMillis

class Coroutines {
    @Test
    fun testRoutines() = runBlocking {
        val time = measureTimeMillis {
            println("Started")
            val deffered1Second = async { take1Second() }.await()
            val deffered2Second = async { take2Second() }
            val deffered3Second = async { take3Second() }.await()
            println("Finished ${deffered1Second} + ${deffered2Second.await()} + ${deffered3Second}")
        }
        println("Total time $time")
    }

    suspend fun take1Second(): String {
        println("Starting 1 second on thread ${Thread.currentThread()}")
        delay(1000)
        println("Finished 1 second")
        return "wow 1"
    }

    suspend fun take2Second(): String {
        println("Starting 2 second on thread ${Thread.currentThread()}")
        delay(2000)
        println("Finished 2 second")
        return "wow 2"
    }

    suspend fun take3Second(): String {
        println("Starting 3 second on thread ${Thread.currentThread()}")
        delay(2000)
        println("Finished 3 second")
        return "wow 3"
    }
}