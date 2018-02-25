package com.jdkgroup.presenter

import com.jdkgroup.baseclass.BasePresenter
import com.jdkgroup.interacter.InterActorCallback
import com.jdkgroup.model.api.Response
import com.jdkgroup.model.api.countrylist.CountryResponse
import com.jdkgroup.model.api.signup.SignUpResponse
import com.jdkgroup.model.request.LoginRequest
import com.jdkgroup.model.request.SignUpRequest
import com.jdkgroup.view.LoginView
import com.jdkgroup.view.SignUpView

import okhttp3.MultipartBody

class LoginPresenter : BasePresenter<LoginView>() {
    fun callApiPostLogin(loginRequest: LoginRequest) {
        appInteractor.apiPostLogin(view!!.activity, loginRequest,  object : InterActorCallback<SignUpResponse> {
            override fun onStart() {
                view!!.showProgressDialog(true)
            }

            override fun onResponse(response: SignUpResponse) {
                view!!.apiPostLogin(response)
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