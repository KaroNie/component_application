package com.karo.common.widgets

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.karo.common.utils.px
import kotlin.math.cos
import kotlin.math.sin

/**
 * DashBoardView
 * description:
 * created at：18:03 on 2020-12-23
 * author: NKY
 * email: karo.nie@esenyun.com
 */

private val RADIUS = 150f.px
private val LENGTH = 120f.px
private const val ANGLE = 120f
private const val TOTAL = 16
private const val INDEX = 2

class DashBoardView(context: Context, attributeSet: AttributeSet?) : View(context, attributeSet) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path = Path()
    private val dash = Path()
    private lateinit var pathEffect: PathDashPathEffect

    init {
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 3f.px
        dash.addRect(0f, 0f, 3f.px, 10f.px, Path.Direction.CCW)
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        path.reset()
        path.addArc(
            width / 2 - RADIUS,
            height / 2 - RADIUS,
            width / 2 + RADIUS,
            height / 2 + RADIUS,
            90f + ANGLE / 2,
            360f - ANGLE
        )
        val pathMeasure = PathMeasure(path, false)
        pathEffect =
            PathDashPathEffect(
                dash,
                (pathMeasure.length - 3f.px) / TOTAL,
                0f,
                PathDashPathEffect.Style.ROTATE
            )
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onDraw(canvas: Canvas) {
        //画圆弧
        canvas.drawPath(path, paint)

        //画刻度
        paint.pathEffect = pathEffect
        canvas.drawPath(path, paint)
        paint.pathEffect = null

        //画指针
        val stopX =
            width / 2 + LENGTH * cos(Math.toRadians((90f + ANGLE / 2f + (360f - ANGLE) / TOTAL * INDEX).toDouble())).toFloat()
        val stopY =
            height / 2 + LENGTH * sin(Math.toRadians((90f + ANGLE / 2f + (360f - ANGLE) / TOTAL * INDEX).toDouble())).toFloat()

        canvas.drawLine(width / 2f, height / 2f, stopX, stopY, paint)

        super.onDraw(canvas)
    }
}