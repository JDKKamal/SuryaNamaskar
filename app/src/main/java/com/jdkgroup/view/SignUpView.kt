package com.jdkgroup.view

import com.jdkgroup.baseclass.BaseView
import com.jdkgroup.model.api.Response
import com.jdkgroup.model.api.countrylist.CountryResponse

interface SignUpView : BaseView {
    fun apiGetCountryList(response: CountryResponse)
}

