package com.jdkgroup.presenter

import com.jdkgroup.baseclass.BasePresenter
import com.jdkgroup.interacter.InterActorCallback
import com.jdkgroup.model.api.faq.FaqListSectionResponse
import com.jdkgroup.model.api.signup.SignUpResponse
import com.jdkgroup.model.request.SignUpRequest
import com.jdkgroup.view.FaqView
import com.jdkgroup.view.LoginView

class FaqPresenter : BasePresenter<FaqView>() {
     fun callApiPostFaqSection() {
        appInteractor.apiPostFaqSection(view!!.activity, object : InterActorCallback<FaqListSectionResponse> {
            override fun onStart() {
                view!!.showProgressDialog(true)
            }

            override fun onResponse(response: FaqListSectionResponse) {
                view!!.apiPostFaqSectionResponse(response)
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