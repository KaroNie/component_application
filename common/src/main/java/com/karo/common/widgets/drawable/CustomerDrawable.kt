package com.karo.common.widgets.drawable

import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable

/**
 * CustomerDrawable
 * description:
 * created at：13:57 on 2020-12-29
 * author: NKY
 * email: karo.nie@esenyun.com
 */

class CustomerDrawable(
    private val interval: Float,
    private val lineWidth: Float,
    private val lineColor: Int
) : Drawable() {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = lineColor
        strokeWidth = lineWidth
    }

    override fun draw(canvas: Canvas) {
        //绘制网格：先绘制竖线，在绘制横线
        var x = bounds.left.toFloat() + lineWidth / 2
        while (x <= bounds.right) {
            canvas.drawLine(x, bounds.top.toFloat(), x, bounds.bottom.toFloat(), paint)
            x += interval
        }
        var y = bounds.top.toFloat() + lineWidth / 2
        while (y <= bounds.bottom) {
            canvas.drawLine(bounds.left.toFloat(), y, bounds.right.toFloat(), y, paint)
            y += interval
        }

    }

    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
    }

    override fun getAlpha(): Int {
        return paint.alpha
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        paint.colorFilter = colorFilter
    }

    override fun getColorFilter(): ColorFilter? {
        return paint.colorFilter
    }

    override fun getOpacity(): Int {
        return when (paint.alpha) {
            0 -> PixelFormat.TRANSPARENT
            0xff -> PixelFormat.OPAQUE
            else -> PixelFormat.TRANSLUCENT
        }
    }
}