package com.jdkgroup.presenter

import android.app.Activity
import com.jdkgroup.baseclass.BasePresenter
import com.jdkgroup.interacter.InterActorCallback
import com.jdkgroup.model.api.signup.SignUpResponse
import com.jdkgroup.model.request.SignUpRequest
import com.jdkgroup.view.ProfileView

class ProfilePresenter : BasePresenter<ProfileView>() {
    public fun callApiPostProfile(activity: Activity, signUpRequest: SignUpRequest) {
        appInteractor.apiPostProfile(activity, signUpRequest, object : InterActorCallback<SignUpResponse> {
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