package com.karo.arouter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import dalvik.system.BaseDexClassLoader
import dalvik.system.DexFile
import java.lang.reflect.Field

/**
 * ARouter
 * description:
 * created at：16:57 on 2020/12/11
 * author: NKY
 * email: karo.nie@esenyun.com
 */
object ARouter {
    private const val packageName = "com.component.util"
    private val map: HashMap<String, Class<out Activity>> = HashMap()
    private lateinit var context: Context

    fun init(context: Context) {
        this.context = context
        val classNames = getClassName(packageName)
        for (className in classNames) {
            try {
                val clazz = Class.forName(className)
                if (IRouter::class.java.isAssignableFrom(clazz)) {
                    val iRouter = clazz.newInstance() as IRouter
                    iRouter.putActivity()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun putActivity(key: String?, clazz: Class<out Activity>?) {
        if (key != null && clazz != null && !map.containsKey(key)) {
            map[key] = clazz
        }
    }

    fun jumpActivity(routePath: String, bundle: Bundle?) {
        val clazz = map[routePath]
        if (null == clazz) {
            Toast.makeText(context, "找不到path：$routePath", Toast.LENGTH_LONG).show()
            return
        }
        val intent = Intent(context, clazz)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

        ActivityCompat.startActivity(context, intent, null)
    }

    private fun getClassName(packageName: String): List<String> {
        val classList = mutableListOf<String>()
        val multiDex = getMultiDex()
        for (dexFile in multiDex) {
            val entries = dexFile.entries()
            while (entries.hasMoreElements()) {
                val className = entries.nextElement()
                if (className.contains(packageName)) {
                    classList.add(className)
                }
            }
        }

        return classList
    }

    private fun getMultiDex(): List<DexFile> {
        val dexLoader = getClassLoader() as? BaseDexClassLoader
        val field =
            getField("pathList", getClassByAddressName("dalvik.system.BaseDexClassLoader")!!)
        val pathList: Any? = getObjectFromField(field, dexLoader)
        val field1 = getField("dexElements", getClassByAddressName("dalvik.system.DexPathList")!!)
        val list: Array<Any>? = getObjectFromField(field1, pathList)
        val field2 =
            getField("dexFile", getClassByAddressName("dalvik.system.DexPathList$${"Element"}")!!)
        val res = mutableListOf<DexFile>()
        if (list != null) {
            for (arg in list) {
                val d: DexFile? = getObjectFromField(field2, arg)
                if (d != null) {
                    res.add(d)
                }
            }
        }

        return res
    }

    private fun getField(className: String, clazz: Class<*>): Field? {
        var field: Field? = null
        try {
            field = clazz.getDeclaredField(className)
            field.isAccessible = true
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return field
    }

    private fun getClassLoader(): ClassLoader? {
        return Thread.currentThread().contextClassLoader;
    }

    private fun getClassByAddressName(classAddressName: String): Class<*>? {
        var mClass: Class<*>? = null
        try {
            mClass = Class.forName(classAddressName)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return mClass
    }

    private fun <T : Any?> getObjectFromField(field: Field?, arg: Any?): T? {
        return try {
            field?.isAccessible = true
            field?.get(arg) as T
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}