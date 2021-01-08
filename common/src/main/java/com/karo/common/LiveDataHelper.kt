package com.karo.common

import androidx.lifecycle.MutableLiveData

/**
 * LiveDataHelper
 * description:
 * created at：18:33 on 2020/11/16
 * author: NKY
 * email: karo.nie@esenyun.com
 */
object LiveDataHelper {
    private val map = HashMap<String, MutableLiveData<Any>>()

    fun <T> whit(key: String, type: Class<T>): MutableLiveData<T> {
        if (!map.containsKey(key)) {
            map[key] = MutableLiveData()
        }
        return map[key] as MutableLiveData<T>
    }
}