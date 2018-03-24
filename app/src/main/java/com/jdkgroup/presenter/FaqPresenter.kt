package com.jdkgroup.presenter

import android.app.Activity
import com.jdkgroup.baseclass.BaseActivity
import com.jdkgroup.baseclass.BasePresenter
import com.jdkgroup.interacter.InterActorCallback
import com.jdkgroup.model.api.faq.FaqListSectionResponse
import com.jdkgroup.view.FaqView

class FaqPresenter : BasePresenter<FaqView>() {
     fun callApiPostFaqSection(activity: Activity) {
        appInteractor.apiPostFaqSection(activity, object : InterActorCallback<FaqListSectionResponse> {
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