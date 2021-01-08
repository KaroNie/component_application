package com.karo.common.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import com.karo.common.utils.px
import com.karo.common.widgets.drawable.CustomerDrawable

/**
 * DrawableView
 * description:
 * created at：13:56 on 2020-12-29
 * author: NKY
 * email: karo.nie@esenyun.com
 */
class DrawableView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val drawable = CustomerDrawable(50f.px, 2f.px, Color.YELLOW)

    override fun onDraw(canvas: Canvas) {
        drawable.setBounds(0, 0, width, height)
        drawable.draw(canvas)
    }
}