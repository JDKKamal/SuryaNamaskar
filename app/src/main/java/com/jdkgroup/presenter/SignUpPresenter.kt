package com.jdkgroup.presenter

import android.app.Activity
import com.jdkgroup.baseclass.BasePresenter
import com.jdkgroup.interacter.InterActorCallback
import com.jdkgroup.model.api.countrylist.CountryResponse
import com.jdkgroup.model.api.signup.SignUpResponse
import com.jdkgroup.model.request.SignUpRequest
import com.jdkgroup.view.SignUpView

class SignUpPresenter : BasePresenter<SignUpView>() {
    fun callApiGetCountryList(activity: Activity) {
        appInteractor.apiGetCountryList(activity, object : InterActorCallback<CountryResponse> {
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

    fun callApiPostSignUp(activity: Activity , signUpRequest: SignUpRequest) {
        appInteractor.apiPostSignUp(activity, signUpRequest,  object : InterActorCallback<SignUpResponse> {
            override fun onStart() {
                view!!.showProgressDialog(true)
            }

            override fun onResponse(response: SignUpResponse) {
                view!!.apiPostSignUp(response)
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