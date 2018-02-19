package com.jdkgroup.model.api.countrylist

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.jdkgroup.model.api.Response

class CountryResponse : Response() {

    @SerializedName("countrylist")
    @Expose
    var listCountry: List<ModelCountry>? = null
    @SerializedName("response")
    @Expose
    private val response: Response? = null
}