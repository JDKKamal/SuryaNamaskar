package com.jdkgroup.suryanamaskar.timeline.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.jdkgroup.suryanamaskar.timeline.model.Timepoint
import kotlinx.android.synthetic.main.itemview_time_point.view.*
import com.jdkgroup.suryanamaskar.R

class TimepointItemDelegateAdapter : ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = WeatherItemViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as WeatherItemViewHolder
        holder.bind(item as Timepoint)
    }

    inner class WeatherItemViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.itemview_time_point, parent, false)) {

        fun bind(item: Timepoint) = with(itemView) {
            time.text = item.timepoint
            time_description.text = item.description
        }

    }

}


