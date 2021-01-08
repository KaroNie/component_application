package com.karo.common

import android.app.Application
import android.content.Context
import android.util.Log

/**
 * BaseApplication
 * description:
 * created at：16:29 on 2020/10/13
 * author: NKY
 * email: karo.nie@esenyun.com
 */
open class BaseApplication : Application() {
    companion object {
        private var isApplication = true
        private lateinit var currentApplication: Context

        //java即可直接调用该方法
        @JvmStatic
        fun currentApplication(): Context {
            return currentApplication
        }

        @JvmStatic
        fun isApplication(): Boolean {
            return isApplication
        }
    }

    override fun onCreate() {
        super.onCreate()
        currentApplication = this
        isApplication = BuildConfig.isApplication
        Log.d("BaseApplication", isApplication.toString())
    }
}