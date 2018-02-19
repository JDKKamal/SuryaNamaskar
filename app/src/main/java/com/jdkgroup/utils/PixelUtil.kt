package com.jdkgroup.utils

import android.content.Context
import android.util.DisplayMetrics
import com.jdkgroup.utils.PixelUtil.getPixelScaleFactor

/**
 * Util class for converting between dp, px and other magical pixel units
 */
object PixelUtil {

    fun dpToPx(context: Context, dp: Int): Int {
        return Math.round(dp * getPixelScaleFactor(context))
    }

    fun pxToDp(context: Context, px: Int): Int {
        return Math.round(px / getPixelScaleFactor(context))
    }

    private fun getPixelScaleFactor(context: Context): Float {
        val displayMetrics = context.resources.displayMetrics
        return displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT
    }

}
