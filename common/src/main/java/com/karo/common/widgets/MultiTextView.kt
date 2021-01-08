package com.karo.common.widgets

import android.content.Context
import android.graphics.*
import android.os.Build
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.karo.common.R
import com.karo.common.utils.px

/**
 * MultiTextView
 * description:
 * created at：18:52 on 2020-12-24
 * author: NKY
 * email: karo.nie@esenyun.com
 */

private val IMAGE_WIDTH = 160f.px
private val IMAGE_PADDING = 60f.px

class MultiTextView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val text =
        "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc."
    private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 22f.px
    }
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bounds = Rect()
    private val fontMetrics = Paint.FontMetrics()
    private val measuredWidth = floatArrayOf(0f)
    private lateinit var staticLayout: StaticLayout


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        staticLayout = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StaticLayout.Builder.obtain(text, 0, text.length, textPaint, width).build()
        } else {
            StaticLayout(text, textPaint, width, Layout.Alignment.ALIGN_NORMAL, 1f, 0f, false)
        }
    }

    override fun onDraw(canvas: Canvas) {
        //绘制图片
        canvas.drawBitmap(getImage(IMAGE_WIDTH.toInt()), width - IMAGE_WIDTH, IMAGE_PADDING, paint)

//        staticLayout.draw(canvas)

//        textPaint.getTextBounds(text, 0, text.length, bounds)

        textPaint.getFontMetrics(fontMetrics)

//        canvas.drawText(text, 0f, 0f - fontMetrics.top, textPaint)

        //计算第一行的数量，从头开始绘制，并记录已绘制的位置
        //当已绘制的长度等于文本总长时，则表示绘制完毕
        val length = text.length
        var start = 0
        var y = -fontMetrics.top
        var maxWidth: Float
        while (start < length) {
            //如果将要绘制的行，文字底部在图片顶部之上或者文字顶部在图片底部之下，宽度为屏幕宽度，否则减去图片宽度
            maxWidth = if ((y + fontMetrics.bottom) < IMAGE_PADDING
                || (y + fontMetrics.top) > IMAGE_PADDING + IMAGE_WIDTH
            ) {
                width.toFloat()
            } else {
                width - IMAGE_WIDTH
            }
            val count =
                textPaint.breakText(text, start, length, true, maxWidth, measuredWidth)
            canvas.drawText(
                text,
                start,
                start + count,
                0f,
                y,
                textPaint
            )
            start += count
            y += textPaint.fontSpacing
        }
    }

    private fun getImage(width: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.avatar, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width
        return BitmapFactory.decodeResource(resources, R.drawable.avatar, options)
    }
}