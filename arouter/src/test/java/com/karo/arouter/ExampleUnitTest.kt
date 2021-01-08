package com.karo.arouter

import org.junit.Test

import org.junit.Assert.*
import kotlin.concurrent.thread

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
        println("dalvik.system.DexPathList$${"Element"}")
        println("线程名：${Thread.currentThread().name}")
        var count = 0
        var printNum = 0
        Thread({
            println("线程名：${Thread.currentThread().name}")
            for (i in 1..10000) {
                count++
                print("count:$i")
            }
        }, "钢铁侠").start()

        thread {
            println("线程名：${Thread.currentThread().name}")
//            while (printNum < 1000) {
//                printNum++
//                println("count还没到10000")
//            }
        }.name = "复仇者联盟"


    }
}
