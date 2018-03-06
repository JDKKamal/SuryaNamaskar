package com.jdkgroup.presenter

import com.jdkgroup.baseclass.BasePresenter
import com.jdkgroup.interacter.InterActorCallback
import com.jdkgroup.model.api.signup.SignUpResponse
import com.jdkgroup.model.request.SignUpRequest
import com.jdkgroup.view.ProfileView

class ProfilePresenter : BasePresenter<ProfileView>() {
    public fun callApiPostProfile(signUpRequest: SignUpRequest) {
        appInteractor.apiPostProfile(view!!.activity, signUpRequest, object : InterActorCallback<SignUpResponse> {
            override fun onStart() {
                view!!.showProgressDialog(true)
            }

            override fun onResponse(response: SignUpResponse) {
                view!!.apiPostProfileResponse(response)
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