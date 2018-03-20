package com.jdkgroup.view

import com.jdkgroup.baseclass.BaseView
import com.jdkgroup.model.api.faq.FaqListSectionResponse

interface FaqView : BaseView {
    fun apiPostFaqSectionResponse(response: FaqListSectionResponse)
}

