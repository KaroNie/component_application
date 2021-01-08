package com.karo.common.utils

import android.content.res.Resources
import android.util.TypedValue

/**
 * Utils
 * description:
 * created at：16:10 on 2020/12/11
 * author: NKY
 * email: karo.nie@esenyun.com
 */

val Float.px: Float
    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, displayMetrics)


private val displayMetrics = Resources.getSystem().displayMetrics

/**
 * dp转换成dx
 */
fun dp2dx(dp: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics)
}

/**
 * sp转换成dx
 */
fun sp2dx(sp: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, displayMetrics)
}