package com.karo.componentapplication

/**
 * User
 * description:
 * created at：17:23 on 2020/12/3
 * author: NKY
 * email: karo.nie@esenyun.com
 */
class User : Person {
    var name: String? = null
    var age: Int? = 0

    constructor()
    constructor(name: String?, age: Int?)

    override fun stature(): Int {
        return 180
    }

    override fun weight(): Int {
        return 70
    }

    override fun eat() {
        println("$name eat food")
    }

    override fun toString(): String {
        return """
            name:$name
            age:$age
        """.trimIndent()
    }

}