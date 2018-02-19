package com.jdkgroup.customview

import android.content.Context
import android.graphics.Typeface

import com.jdkgroup.constant.AppConstant

class FontTypeface(private val context: Context) {

    val typefaceAndroid: Typeface
        get() {
            var typeFace = Typeface.DEFAULT
            val strFont = "Assets/" + AppConstant.FONT_AILERON_REGULAR
            typeFace = Typeface.createFromAsset(context.assets, strFont.replace("Assets/", ""))
            return typeFace
        }
}