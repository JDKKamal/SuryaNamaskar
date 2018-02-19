package com.jdkgroup.baseclass

//TODO DEVELOPED BY KAMLESH LAKHANI

import android.app.Activity

interface BaseView {
    val defaultParameter: Map<String, String>
    val activity: Activity
    fun hasInternet(): Boolean
    fun showProgressDialog(show: Boolean)
    //void onSuccess(T response);
    //void onSuccess(List<T> response);
    fun onFailure(message: String)
}