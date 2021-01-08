package com.karo.componentapplication

/**
 * Person
 * description:
 * created at：15:26 on 2020/12/14
 * author: NKY
 * email: karo.nie@esenyun.com
 */
abstract class Person {
    open fun stature(): Int {
        return 0
    }

    open fun weight(): Int {
        return 0
    }

    open fun eat() {
        println("我喜欢吃肉")
    }
}