package com.karo.common.widgets

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.karo.common.R
import com.karo.common.utils.px

/**
 * AvatarView
 * description:
 * created at：14:56 on 2020-12-24
 * author: NKY
 * email: karo.nie@esenyun.com
 */
class AvatarView(context: Context, attributeSet: AttributeSet?) : View(context, attributeSet) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    private val bound = RectF(50f.px, 50f.px, 200f.px, 200f.px)
    private val clipPath = Path().apply {
        addOval(bound, Path.Direction.CCW)
    }

    override fun onDraw(canvas: Canvas) {

        //方法一
//        val count = canvas.saveLayer(bound, null)
//
//        canvas.drawOval(bound, paint)
//        paint.xfermode = xfermode
//        canvas.drawBitmap(getBitmap(150f.px.toInt()), 50f.px, 50f.px, paint)
//        paint.xfermode = null
//
//        canvas.restoreToCount(count)

        //方法二：范围裁切，有毛边
        canvas.clipPath(clipPath)
        canvas.drawBitmap(getBitmap(150f.px.toInt()), 50f.px, 50f.px, paint)
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