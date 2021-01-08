package com.karo.common.utils

import android.widget.Toast
import com.karo.common.BaseApplication

/**
 * ToastUtils
 * description:
 * created at：15:41 on 2020/12/11
 * author: NKY
 * email: karo.nie@esenyun.com
 */
object ToastUtils {
    fun toast(content: String?) {
        toast(content, Toast.LENGTH_LONG)
    }

    fun toast(content: String?, duration: Int) {
        toast(content, duration, -1)
    }

    fun toast(content: String?, duration: Int, gravity: Int) {
        if (content == null || content.trim().isEmpty()) return
        val textToast =
            Toast.makeText(BaseApplication.currentApplication(), content, duration)
        if (gravity != -1) {
            textToast.setGravity(gravity, 0, 0)
        }
        textToast.show()
    }

}