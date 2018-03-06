package com.jdkgroup.view

import com.jdkgroup.baseclass.BaseView
import com.jdkgroup.model.api.signup.SignUpResponse

interface ProfileView : BaseView {
    fun apiPostProfileResponse(response: SignUpResponse)
}

