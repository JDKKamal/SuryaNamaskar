package com.jdkgroup.model.api.signup

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ModelSignUp {

    @SerializedName("userid")
    @Expose
    var userid: String? = null
    @SerializedName("username")
    @Expose
    var username: String? = null
    @SerializedName("email")
    @Expose
    var email: String? = null
    @SerializedName("mobile")
    @Expose
    var mobile: String? = null
    @SerializedName("gender")
    @Expose
    var gender: Int? = null

}