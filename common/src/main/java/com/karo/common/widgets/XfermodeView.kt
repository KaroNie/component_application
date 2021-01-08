package com.karo.common.widgets

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.karo.common.utils.px

/**
 * XfermodeView
 * description:
 * created at：15:30 on 2020-12-24
 * author: NKY
 * email: karo.nie@esenyun.com
 */
class XfermodeView(context: Context, attributeSet: AttributeSet?) : View(context, attributeSet) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_ATOP)
    private val bound = RectF(0f, 0f, 150f.px, 150f.px)

    private val circleBitmap =
        Bitmap.createBitmap(150f.px.toInt(), 150f.px.toInt(), Bitmap.Config.ARGB_8888)
    private val squareBitmap =
        Bitmap.createBitmap(150f.px.toInt(), 150f.px.toInt(), Bitmap.Config.ARGB_8888)

    init {
        var canvas = Canvas(circleBitmap)
        paint.color = Color.BLUE
        canvas.drawOval(50f.px, 0f, 150f.px, 100f.px, paint)
        canvas = Canvas(squareBitmap)
        paint.color = Color.RED
        canvas.drawRect(0f, 50f.px, 100f.px, 150f.px, paint)
    }

    override fun onDraw(canvas: Canvas) {
        val count = canvas.saveLayer(bound, null)
        canvas.drawBitmap(circleBitmap, 0f, 0f, paint)
        paint.xfermode = xfermode
        canvas.drawBitmap(squareBitmap, 0f, 0f, paint)
        paint.xfermode = null

        canvas.restoreToCount(count)
    }
}