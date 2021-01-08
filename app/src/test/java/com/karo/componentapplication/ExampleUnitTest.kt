package com.karo.componentapplication

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
        main()
        val doubleNum = doubleNum(3)
        val doubleNum1 = doubleNum(6)
        var age = 18
        val name = "kaiyaun"
        val user = User(name, age)
        user.age = age
        user.name = name

        user.eat()
        user.stature()
        user.weight()

        println(user.toString())

        val intArrayOf = intArrayOf(10)
        val stringList = mutableListOf<String>()
        val userList = mutableListOf<User>()
        val userMap = mutableMapOf<String, User>()
        userMap["hahah"] = User("kaiyuan", 20)
    }

    private fun main() {
        for (i in 0..10) {
            //包头包尾
            println("遍历$i")
        }

        println("这是一个打印方法")
    }

    private fun doubleNum(x: Int): Int {
        return x * 2
    }
}
