package com.jdkgroup.model.api.signup

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.jdkgroup.model.api.Response

class SignUpResponse {

    @SerializedName("response")
    @Expose
    var response: Response? = null
    @SerializedName("signup")
    @Expose
    var signup: ModelSignUp? = null

}