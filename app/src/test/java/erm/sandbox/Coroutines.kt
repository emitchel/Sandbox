package erm.sandbox

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Test
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class Coroutines {
    @Test
    fun testRoutines() {
        myblockingmethod()
    }

    suspend fun printlnDelayed(message: String) {
        // Complex calculation
        delay(1000)
        println(message)
    }

    suspend fun calculateHardThings(startNum: Int): Int {
        delay(1000)
        return startNum * 10
    }

    fun exampleBlocking() = runBlocking {
        println("one")
        printlnDelayed("two")
        println("three")
    }

    // Running on another thread but still blocking the main thread
    fun exampleBlockingDispatcher(){
        runBlocking(Dispatchers.Default) {
            println("one - from thread ${Thread.currentThread().name}")
            printlnDelayed("two - from thread ${Thread.currentThread().name}")
        }
        // Outside of runBlocking to show that it's running in the blocked main thread
        println("three - from thread ${Thread.currentThread().name}")
        // It still runs only after the runBlocking is fully executed.
    }

    fun exampleLaunchGlobal() = runBlocking {
        println("one - from thread ${Thread.currentThread().name}")

        GlobalScope.launch {
            printlnDelayed("two - from thread ${Thread.currentThread().name}")
        }

        println("three - from thread ${Thread.currentThread().name}")
        delay(3000)
    }

    fun exampleLaunchGlobalWaiting() = runBlocking {
        println("one - from thread ${Thread.currentThread().name}")

        val job = GlobalScope.launch {
            printlnDelayed("two - from thread ${Thread.currentThread().name}")
        }

        println("three - from thread ${Thread.currentThread().name}")
        job.join()
    }

    fun exampleLaunchCoroutineScope() = runBlocking {
        println("one - from thread ${Thread.currentThread().name}")

        val customDispatcher = Executors.newFixedThreadPool(2).asCoroutineDispatcher()

        launch(customDispatcher) {
            printlnDelayed("two - from thread ${Thread.currentThread().name}")
        }

        println("three - from thread ${Thread.currentThread().name}")

        (customDispatcher.executor as ExecutorService).shutdown()
    }

    fun exampleAsyncAwait() = runBlocking {
        val startTime = System.currentTimeMillis()

        val deferred1 = async { calculateHardThings(10) }
        val deferred2 = async { calculateHardThings(20) }
        val deferred3 = async { calculateHardThings(30) }

        val sum = deferred1.await() + deferred2.await() + deferred3.await()
        println("async/await result = $sum")

        val endTime = System.currentTimeMillis()
        println("Time taken: ${endTime - startTime}")
    }

    fun exampleWithContext() = runBlocking {
        val startTime = System.currentTimeMillis()

        val result1 = withContext(Dispatchers.Default) { calculateHardThings(10) }
        val result2 = withContext(Dispatchers.Default) { calculateHardThings(20) }
        val result3 = withContext(Dispatchers.Default) { calculateHardThings(30) }

        val sum = result1 + result2 + result3
        println("async/await result = $sum")

        val endTime = System.currentTimeMillis()
        println("Time taken: ${endTime - startTime}")
    }

    fun myblockingmethod() = runBlocking {
        launch {  }
        println("Started")
        val deffered1Second = async { take1Second() }.await()
        val deffered2Second = async { take2Second() }
        val deffered3Second = async { take3Second() }.await()
        println("Finished ${deffered1Second} + ${deffered2Second.await()} + ${deffered3Second}")
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