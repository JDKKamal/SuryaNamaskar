package com.jdkgroup.suryanamaskar.timeline.model

import com.jdkgroup.suryanamaskar.timeline.adapter.ViewType

class Weather(val date: String,
              val weatherDescription: String,
              val temperature: Float,
              val isLastItem: Boolean = false) : ViewType {

    override fun getViewType(): Int  = ViewType.ITEM

}
