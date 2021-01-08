package com.karo.common.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import com.karo.common.utils.px

/**
 * SportView
 * description:
 * created at：18:06 on 2020-12-24
 * author: NKY
 * email: karo.nie@esenyun.com
 */
private val RADIUS = 120f.px

class SportView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = 15f.px
        style = Paint.Style.STROKE
        color = Color.GRAY
    }
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textAlign = Paint.Align.CENTER
        color = Color.RED
    }
    private val bounds = Rect()
    private val fontMetrics = Paint.FontMetrics()

    override fun onDraw(canvas: Canvas) {
        //绘制圆环
        canvas.drawCircle(width / 2f, height / 2f, RADIUS, paint)
        //绘制进度
        paint.color = Color.RED
        paint.strokeCap = Paint.Cap.ROUND
        canvas.drawArc(
            width / 2f - RADIUS,
            height / 2f - RADIUS,
            width / 2f + RADIUS,
            height / 2f + RADIUS,
            -90f, 260f, false,
            paint
        )

        //绘制文字
//        textPaint.getTextBounds("776", 0, "776".length, bounds)
//        canvas.drawText(
//            "776",
//            width / 2f,
//            height / 2f - (bounds.top + bounds.bottom) / 2,
//            textPaint
//        )

        textPaint.textSize = 80f.px
        textPaint.getFontMetrics(fontMetrics)
        canvas.drawText(
            "abab",
            width / 2f,
            height / 2f - (fontMetrics.descent + fontMetrics.ascent) / 2,
            textPaint
        )

        textPaint.textSize = 20f.px
        textPaint.textAlign = Paint.Align.LEFT
        textPaint.getTextBounds("aqaq", 0, "aqaq".length, bounds)
        canvas.drawText(
            "aqaq",
            0f - bounds.left,
            0f - bounds.top,
            textPaint
        )

        textPaint.textSize = 100f.px
        textPaint.textAlign = Paint.Align.LEFT
        textPaint.getTextBounds("abab", 0, "abab".length, bounds)
        canvas.drawText(
            "abab",
            0f - bounds.left,
            0f - bounds.top,
            textPaint
        )
    }
}