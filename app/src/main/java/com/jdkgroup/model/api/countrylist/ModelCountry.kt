package com.jdkgroup.model.api.countrylist

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ModelCountry {

    @SerializedName("code")
    @Expose
    var code: String? = null
    @SerializedName("flag")
    @Expose
    var flag: String? = null
    @SerializedName("id")
    @Expose
    var id: Long? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("shortname")
    @Expose
    var shortname: String? = null
    @SerializedName("status")
    @Expose
    var status: Long? = null
    @SerializedName("symbol")
    @Expose
    var symbol: String? = null

}