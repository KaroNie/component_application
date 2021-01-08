package com.karo.common.utils

import android.content.Context
import com.karo.common.BaseApplication
import com.karo.common.R

/**
 * CacheUtile
 * description:
 * created at：15:37 on 2020/12/11
 * author: NKY
 * email: karo.nie@esenyun.com
 */
object CacheUtils {
    private val context = BaseApplication.currentApplication()
    private val sp =
        context.getSharedPreferences(context.getString(R.string.sp_name), Context.MODE_PRIVATE)

    fun saveString(key: String?, value: String?) {
        if (key == null) return
        sp.edit().putString(key, value).apply()
    }

    fun getString(key: String?): String? {
        if (key == null) return null
        return sp.getString(key, null)
    }

    fun remove(key: String?) {
        if (key == null) return
        sp.edit().remove(key).apply()
    }

    fun clear() {
        sp.edit().clear().apply()
    }
}