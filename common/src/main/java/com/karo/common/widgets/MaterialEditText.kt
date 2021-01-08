package com.karo.common.widgets

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.karo.common.R
import com.karo.common.utils.px

/**
 * MaterialEditText
 * description:
 * created at：15:55 on 2020-12-29
 * author: NKY
 * email: karo.nie@esenyun.com
 */
private val TEXT_SIZE = 16f.px
private val TEXT_MARGIN = 8f.px
private val VERTICAL_OFFSET = 24f.px
private val EXTRA_VERTICAL_OFFSET = 16f.px
private val HORIZONTAL_OFFSET = 5f.px

class MaterialEditText(context: Context, attrs: AttributeSet?) : AppCompatEditText(context, attrs) {
    private val floatingPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = TEXT_SIZE
        color = Color.RED
    }
    var floatingLabelFraction = 0f
        set(value) {
            field = value
            invalidate()
        }
    var floatingLabelShown = false

    private var userFloatingLabel = false
        set(value) {
            //只有属性值发生变化时有效，避免重复计算
            if (field != value) {
                field = value
                if (field) {
                    setPadding(
                        paddingLeft,
                        paddingTop + (TEXT_SIZE + TEXT_MARGIN).toInt(),
                        paddingRight,
                        paddingBottom
                    )
                } else {
                    setPadding(
                        paddingLeft,
                        paddingTop - (TEXT_SIZE + TEXT_MARGIN).toInt(),
                        paddingRight,
                        paddingBottom
                    )
                }
            }
        }

    init {
        val typedArray = context.obtainStyledAttributes(attrs, intArrayOf(R.attr.userFloatingLabel))
        userFloatingLabel = typedArray.getBoolean(0, true)
        typedArray.recycle()
    }

    private val animator by lazy {
        ObjectAnimator.ofFloat(this, "floatingLabelFraction", 1f)
    }

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        if (userFloatingLabel) {
            if (!floatingLabelShown && !text.isNullOrEmpty()) {
                //1、由空到非空
                floatingLabelShown = !floatingLabelShown
                animator.start()
            } else if (floatingLabelShown && text.isNullOrEmpty()) {
                //1、由非空到空
                floatingLabelShown = !floatingLabelShown
                animator.reverse()
            }
        }

        super.onTextChanged(text, start, lengthBefore, lengthAfter)
    }

    override fun onDraw(canvas: Canvas) {
        if (userFloatingLabel) {
            //随着输入内容变化，floatingLabel的透明度也同步变化
            floatingPaint.alpha = (floatingLabelFraction * 0xff).toInt()
            //floatingLabel显示动画：从hint的位置移动到顶部
            val currentVerticalOffset = VERTICAL_OFFSET + EXTRA_VERTICAL_OFFSET * (1 - floatingLabelFraction)
            canvas.drawText(
                hint.toString(),
                HORIZONTAL_OFFSET,
                currentVerticalOffset,
                floatingPaint
            )
        }

        super.onDraw(canvas)

    }
}