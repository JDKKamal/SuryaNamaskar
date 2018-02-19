package com.jdkgroup.customview.recyclerview

import android.content.Context
import android.support.v4.widget.SwipeRefreshLayout
import android.util.AttributeSet

import com.jdkgroup.suryanamaskar.R

class CustomSwipeRefresh : SwipeRefreshLayout {
    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    private fun init() {
        setColorSchemeResources(R.color.colorAccent)

    }

    override fun canChildScrollUp(): Boolean {
        return false
    }
}
