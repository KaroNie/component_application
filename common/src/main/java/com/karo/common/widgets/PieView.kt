package com.karo.common.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.karo.common.utils.px
import kotlin.math.cos
import kotlin.math.sin

/**
 * 饼图
 * description:
 * created at：10:23 on 2020-12-24
 * author: NKY
 * email: karo.nie@esenyun.com
 */
private val RADIUS = 150f.px
private val ANGLES = arrayOf(60f, 90f, 120f, 90f)
private val COLORS = listOf(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW)

class PieView(context: Context, attributeSet: AttributeSet?) : View(context, attributeSet) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas: Canvas) {
        var startAngle = 0f

        ANGLES.forEachIndexed { index, angle ->
            if (index == 2) {
                canvas.save()
                val theAngle = startAngle + angle / 2
                val radian = Math.toRadians(theAngle.toDouble())
                canvas.translate(
                    20f * cos(radian).toFloat(),
                    20f * sin(radian).toFloat()
                )
            }
            paint.color = COLORS[index]
            canvas.drawArc(
                width / 2f - RADIUS,
                height / 2f - RADIUS,
                width / 2f + RADIUS,
                height / 2f + RADIUS,
                startAngle,
                angle,
                true,
                paint
            )

            startAngle += angle
            if (index == 2) {
                canvas.restore()
            }
        }
        super.onDraw(canvas)
    }
}