package com.karo.common.widgets

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.karo.common.R
import com.karo.common.utils.px

/**
 * CameraView
 * description:
 * created at：11:36 on 2020-12-28
 * author: NKY
 * email: karo.nie@esenyun.com
 */
private val BITMAP_SIZE = 200f.px
private val BITMAP_PADDING = 100f.px

class CameraView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bitmap = getBitmap(BITMAP_SIZE.toInt())
    private val camera = Camera().apply {
        //屏幕适配
        setLocation(0f, 0f, -6 * resources.displayMetrics.density)
    }

    init {
        camera.rotateX(30f)
    }

    override fun onDraw(canvas: Canvas) {
        //旋转的原生坐标（0,0,0），所以旋转之前，先将图像平移到camera垂直的位置，旋转后，再平移回原来位置

        //上半部分
        canvas.save()
        canvas.translate(BITMAP_PADDING + BITMAP_SIZE / 2, BITMAP_PADDING + BITMAP_SIZE / 2)
        canvas.rotate(-30f)
        canvas.clipRect(-BITMAP_SIZE, -BITMAP_SIZE, BITMAP_SIZE, 0f)
        canvas.rotate(30f)
        canvas.translate(-(BITMAP_PADDING + BITMAP_SIZE / 2), -(BITMAP_PADDING + BITMAP_SIZE / 2))
        canvas.drawBitmap(bitmap, BITMAP_PADDING, BITMAP_PADDING, paint)
        canvas.restore()

        //下半部分
        canvas.save()
        canvas.translate(BITMAP_PADDING + BITMAP_SIZE / 2, BITMAP_PADDING + BITMAP_SIZE / 2)
        canvas.rotate(-30f)
        camera.applyToCanvas(canvas)
        canvas.clipRect(-BITMAP_SIZE, 0f, BITMAP_SIZE, BITMAP_SIZE)
        canvas.rotate(30f)
        canvas.translate(-(BITMAP_PADDING + BITMAP_SIZE / 2), -(BITMAP_PADDING + BITMAP_SIZE / 2))
        canvas.drawBitmap(bitmap, BITMAP_PADDING, BITMAP_PADDING, paint)
        canvas.restore()
    }

    private fun getBitmap(width: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.avatar, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width
        return BitmapFactory.decodeResource(resources, R.drawable.avatar, options)
    }
}