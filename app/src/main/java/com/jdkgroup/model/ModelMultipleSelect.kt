package com.jdkgroup.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ModelMultipleSelect {

    @SerializedName("category_id")
    @Expose
    var categoryId: String? = null
    @SerializedName("category_name")
    @Expose
    var categoryName: String? = null


    var isChecked = false

    constructor() {}

    constructor(categoryId: String, categoryName: String) {

        this.categoryId = categoryId
        this.categoryName = categoryName
    }

}