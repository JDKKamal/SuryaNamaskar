package com.jdkgroup.view

import com.jdkgroup.baseclass.BaseView
import com.jdkgroup.model.api.Response
import com.jdkgroup.model.api.countrylist.CountryResponse
import com.jdkgroup.model.api.signup.SignUpResponse

interface LoginView : BaseView {
    fun apiPostLogin(response: SignUpResponse)
}

