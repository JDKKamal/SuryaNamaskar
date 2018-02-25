package com.jdkgroup.suryanamaskar.activity

import android.os.Bundle
import com.jdkgroup.baseclass.SimpleMVPActivity
import com.jdkgroup.model.api.Response
import com.jdkgroup.model.api.countrylist.CountryResponse
import com.jdkgroup.model.api.countrylist.ModelCountry
import com.jdkgroup.model.api.signup.SignUpResponse
import com.jdkgroup.model.request.LoginRequest
import com.jdkgroup.model.request.SignUpRequest
import com.jdkgroup.presenter.LoginPresenter
import com.jdkgroup.presenter.SignUpPresenter
import com.jdkgroup.suryanamaskar.R
import com.jdkgroup.suryanamaskar.dialog.SpDialogCountry
import com.jdkgroup.utils.AppUtils
import com.jdkgroup.utils.Logging
import com.jdkgroup.view.LoginView
import com.jdkgroup.view.SignUpView

class LoginActivity : SimpleMVPActivity<LoginPresenter, LoginView>(), LoginView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_activity)

        hideSoftKeyboard()

        presenter!!.callApiPostLogin(LoginRequest( "email", "password"));
    }

    override fun createPresenter(): LoginPresenter {
        return LoginPresenter()
    }

    override fun attachView(): LoginView {
        return this
    }

    override fun onFailure(message: String) {
        AppUtils.showToast(this, message + "")
    }

    override fun apiPostLogin(response: SignUpResponse) {
        if (response.response!!.status == 200)
        {

        }

        AppUtils.showToast(this, response.response!!.message + "")
    }
}