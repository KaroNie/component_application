package com.karo.task

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.karo.common.LiveDataHelper

/**
 * TaskViewModel
 * description:
 * created at：14:21 on 2020/10/16
 * author: NKY
 * email: karo.nie@esenyun.com
 */
class TaskViewModel : ViewModel() {
    lateinit var liveData: MutableLiveData<String>

    fun initData() {
        liveData = LiveDataHelper.whit("eat", String::class.java)
        liveData.postValue("哈啊哈和拉菲哥很拉风了呢")
    }
}