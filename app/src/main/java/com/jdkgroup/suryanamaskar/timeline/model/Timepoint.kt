package com.jdkgroup.suryanamaskar.timeline.model

import com.jdkgroup.suryanamaskar.timeline.adapter.ViewType

data class Timepoint(val timepoint: String,
                     val description: String) : ViewType {

    override fun getViewType(): Int = ViewType.LINE

}
