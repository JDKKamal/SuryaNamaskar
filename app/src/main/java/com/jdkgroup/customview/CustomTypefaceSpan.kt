package com.jdkgroup.customview

import android.content.res.Resources
import android.graphics.Paint
import android.graphics.Typeface
import android.text.TextPaint
import android.text.style.TypefaceSpan
import android.util.TypedValue

class CustomTypefaceSpan(family: String, private val newType: Typeface, private val newSize: Int) : TypefaceSpan(family) {

    override fun updateDrawState(ds: TextPaint) {
        applyCustomTypeFace(ds, newType, newSize)
    }

    override fun updateMeasureState(paint: TextPaint) {
        applyCustomTypeFace(paint, newType, newSize)
    }

    companion object {

        private fun applyCustomTypeFace(paint: Paint, tf: Typeface, size: Int) {

            try {
                val oldStyle: Int
                val old = paint.typeface
                if (old == null) {
                    oldStyle = 0
                } else {
                    oldStyle = old.style
                }

                val fake = oldStyle and tf.style.inv()
                if (fake and Typeface.BOLD != 0) {
                    paint.isFakeBoldText = true
                }

                if (fake and Typeface.ITALIC != 0) {
                    paint.textSkewX = -0.25f
                }

                paint.textSize = getPixelsFromDip(size.toFloat())
                paint.typeface = tf
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

        fun getPixelsFromDip(dip: Float): Float {
            return TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, dip,
                    Resources.getSystem().displayMetrics
            )
        }
    }
}