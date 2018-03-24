package com.jdkgroup.baseclass

//TODO DEVELOPED BY KAMLESH LAKHANI

interface BaseView {
    val defaultParameter: Map<String, String>
    fun hasInternet(): Boolean
    fun showProgressDialog(show: Boolean)
    fun onFailure(message: String)
}