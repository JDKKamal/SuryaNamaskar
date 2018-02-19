package com.jdkgroup.model.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.util.Date

/**
 * Created by Lakhani on 12/5/2017.
 */

open class Response {

    @SerializedName("date")
    @Expose
    var date: String? = null
    @SerializedName("message")
    @Expose
    var message: String? = null
    @SerializedName("status")
    @Expose
    var status: Int = 0
    @SerializedName("imagepath")
    @Expose
    var imagepath: String? = null
}