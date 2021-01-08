package com.karo.componentapplication

import com.karo.arouter.ARouter
import com.karo.common.BaseApplication

/**
 * MainApplication
 * description:
 * created at：17:40 on 2020/10/13
 * author: NKY
 * email: karo.nie@esenyun.com
 */
class MainApplication : BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        ARouter.init(this)
    }
}