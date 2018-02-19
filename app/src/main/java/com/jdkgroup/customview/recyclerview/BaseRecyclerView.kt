package com.jdkgroup.customview.recyclerview

import android.support.v7.widget.RecyclerView

abstract class BaseRecyclerView<T> : RecyclerView.Adapter<BaseViewHolder<T>>() {

    protected abstract fun getItem(position: Int): T

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.populateItem(getItem(position))
    }
}
