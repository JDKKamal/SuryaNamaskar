package com.jdkgroup.customview.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.View

import butterknife.ButterKnife

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun populateItem(t: T)

    init {
        ButterKnife.bind(this, itemView)
    }
}
