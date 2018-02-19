package com.jdkgroup.presenter

import com.jdkgroup.baseclass.BasePresenter
import com.jdkgroup.interacter.InterActorCallback
import com.jdkgroup.model.api.Response
import com.jdkgroup.model.api.countrylist.CountryResponse
import com.jdkgroup.view.SignUpView

import okhttp3.MultipartBody

class SignUpPresenter : BasePresenter<SignUpView>() {
    fun callApiGetCountryList() {
        appInteractor.apiGetCountryList(view!!.activity, object : InterActorCallback<CountryResponse> {
            override fun onStart() {
                view!!.showProgressDialog(true)
            }

            override fun onResponse(response: CountryResponse) {
                view!!.apiGetCountryList(response)
            }

            override fun onFinish() {
                view!!.showProgressDialog(false)
            }

            override fun onError(message: String) {
                view!!.onFailure(message)
            }

        })
    }
}