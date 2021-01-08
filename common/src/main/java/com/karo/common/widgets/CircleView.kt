package com.karo.common.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.karo.common.utils.px

/**
 * CircleView
 * description:
 * created at：16:56 on 2020-12-28
 * author: NKY
 * email: karo.nie@esenyun.com
 */
class CircleView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.YELLOW
    }
    var radius = 30f.px
        set(value) {
            field = value
            invalidate()
        }

    override fun onDraw(canvas: Canvas) {
        canvas.drawCircle(width / 2f, height / 2f, radius, paint)

    }
}