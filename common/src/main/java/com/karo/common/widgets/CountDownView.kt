package com.karo.common.widgets

import android.animation.TypeEvaluator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.karo.common.utils.px

/**
 * CountDownView
 * description:
 * created at：17:10 on 2020-12-28
 * author: NKY
 * email: karo.nie@esenyun.com
 */
val numbers = listOf(
    "10",
    "9",
    "8",
    "7",
    "6",
    "5",
    "4",
    "3",
    "2",
    "1"
)

class CountDownView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLUE
        textAlign = Paint.Align.CENTER
        textSize = 30f.px
    }
    var textContent = "10"
        set(value) {
            field = value
            invalidate()
        }
    var textSize = 30f.px
        set(value) {
            field = value
            invalidate()
        }

    override fun onDraw(canvas: Canvas) {
        paint.textSize = textSize
        canvas.drawText(textContent, width / 2f, height / 2f, paint)
    }

}

class CountDownEvaluator : TypeEvaluator<String> {
    override fun evaluate(fraction: Float, startValue: String, endValue: String): String {
        val startIndex = numbers.indexOf(startValue)
        val endIndex = numbers.indexOf(endValue)
        val currentIndex = startIndex + (endIndex - startIndex) * fraction
        return numbers[currentIndex.toInt()]

    }
}