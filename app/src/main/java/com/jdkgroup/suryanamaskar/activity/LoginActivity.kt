package com.jdkgroup.suryanamaskar.activity

import android.os.Bundle
import android.support.v7.widget.AppCompatButton
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.AppCompatTextView
import com.jdkgroup.baseclass.SimpleMVPActivity
import com.jdkgroup.constant.RestConstant
import com.jdkgroup.model.api.signup.SignUpResponse
import com.jdkgroup.model.request.LoginRequest
import com.jdkgroup.presenter.LoginPresenter
import com.jdkgroup.suryanamaskar.R
import com.jdkgroup.utils.AppUtils
import com.jdkgroup.utils.Preference
import com.jdkgroup.utils.Validator
import com.jdkgroup.view.LoginView

class LoginActivity : SimpleMVPActivity<LoginPresenter, LoginView>(), LoginView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        hideSoftKeyboard()

        var appEdtEmail = findViewById<AppCompatEditText>(R.id.appEdtEmail)
        var appEdtPassword = findViewById<AppCompatEditText>(R.id.appEdtPassword)
        var appBtnLogin = findViewById<AppCompatButton>(R.id.appBtnLogin)
        var appTvSignUp = findViewById<AppCompatTextView>(R.id.appTvSignUp);

        //TODO LOGIN
        appBtnLogin.setOnClickListener(
                {
                    val email = appEdtEmail.text.toString();
                    val password = appEdtPassword.text.toString();
                    if (validation(email, password)) {
                        presenter!!.callApiPostLogin(LoginRequest(email, "password"))
                    }
                }
        )

        appTvSignUp.setOnClickListener(
                {
                    AppUtils.startActivity(this, SignUpActivity::class.java)
                }
        )
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
        if (response.response!!.status == 200) {
            Preference.preferenceInstance(this).isLogin = true
        } else if (response.response!!.status == RestConstant.not_found_404) {
            findViewById<AppCompatEditText>(R.id.appEdtPassword).setText("")
        }

        AppUtils.showToast(this, response.response!!.message + "")
    }

    private fun validation(email: String, password: String): Boolean {
        return when {
            Validator.isEmpty(email) -> {
                AppUtils.showToast(this, getString(R.string.msg_empty_email));
                false;
            }
            !Validator.isRegexValidator(email, Validator.patternEmail) -> {
                AppUtils.showToast(this, getString(R.string.msg_valid_email));
                false;
            }
            Validator.isEmpty(password) -> {
                AppUtils.showToast(this, getString(R.string.msg_empty_password));
                false;
            }
            else -> true
        }
    }
}