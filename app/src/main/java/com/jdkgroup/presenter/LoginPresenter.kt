package com.jdkgroup.presenter

import android.app.Activity
import com.jdkgroup.baseclass.BasePresenter
import com.jdkgroup.constant.RestConstant
import com.jdkgroup.interacter.InterActorCallback
import com.jdkgroup.model.api.signup.SignUpResponse
import com.jdkgroup.model.request.SignUpRequest
import com.jdkgroup.suryanamaskar.R
import com.jdkgroup.utils.AppUtils
import com.jdkgroup.utils.Validator
import com.jdkgroup.view.LoginView

class LoginPresenter : BasePresenter<LoginView>() {
    private fun callApiPostLogin(activity: Activity , signUpRequest: SignUpRequest) {
        appInteractor.apiPostLogin(activity, signUpRequest, object : InterActorCallback<SignUpResponse> {
            override fun onStart() {
                view!!.showProgressDialog(true)
            }

            override fun onResponse(response: SignUpResponse) {
                view!!.apiPostLoginResponse(response)
            }

            override fun onFinish() {
                view!!.showProgressDialog(false)
            }

            override fun onError(message: String) {
                view!!.onFailure(message)
            }

        })
    }

    fun apiCall(activity: Activity, apiNo: Int, signUpRequest: SignUpRequest) {
        if (apiNo == RestConstant.CALL_API_LOGIN) {
            callApiPostLogin(activity, signUpRequest)
        }
    }

    //TODO VALIDATION
    fun validation(email: String, password: String, activity: Activity): Boolean {
        return when {
            Validator.isEmpty(email) -> {
                AppUtils.showToast(activity, activity.getString(R.string.msg_empty_email))
                false
            }
            !Validator.isRegexValidator(email, Validator.patternEmail) -> {
                AppUtils.showToast(activity, activity.getString(R.string.msg_valid_email))
                false
            }
            Validator.isEmpty(password) -> {
                AppUtils.showToast(activity, activity.getString(R.string.msg_empty_password))
                false
            }
            else -> true
        }
    }
    //FINISH VALIDATION
}