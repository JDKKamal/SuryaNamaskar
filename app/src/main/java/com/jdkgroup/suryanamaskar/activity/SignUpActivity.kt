package com.jdkgroup.suryanamaskar.activity

import android.os.Bundle
import android.support.v7.widget.AppCompatButton
import android.support.v7.widget.AppCompatEditText
import com.jdkgroup.baseclass.SimpleMVPActivity
import com.jdkgroup.constant.RestConstant
import com.jdkgroup.model.api.countrylist.CountryResponse
import com.jdkgroup.model.api.countrylist.ModelCountry
import com.jdkgroup.model.api.signup.SignUpResponse
import com.jdkgroup.model.request.SignUpRequest
import com.jdkgroup.presenter.SignUpPresenter
import com.jdkgroup.suryanamaskar.R
import com.jdkgroup.suryanamaskar.dialog.SpDialogCountry
import com.jdkgroup.utils.AppUtils
import com.jdkgroup.utils.Logging
import com.jdkgroup.utils.Validator
import com.jdkgroup.view.SignUpView

class SignUpActivity : SimpleMVPActivity<SignUpPresenter, SignUpView>(), SignUpView {
    private var listCountry: List<ModelCountry>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_activity)

        hideSoftKeyboard()

        var appEdtUserName = findViewById<AppCompatEditText>(R.id.appEdtUserName)
        var appEdtEmail = findViewById<AppCompatEditText>(R.id.appEdtEmail)
        var appEdtPassword = findViewById<AppCompatEditText>(R.id.appEdtPassword)
        var appEdtConfirmPassword = findViewById<AppCompatEditText>(R.id.appEdtConfirmPassword)
        var appEdtMobile = findViewById<AppCompatEditText>(R.id.appEdtMobile)
        var appBtnSignUp = findViewById<AppCompatButton>(R.id.appBtnSignUp)

        //TODO SIGN UP
        appBtnSignUp.setOnClickListener({
            var userName = appEdtUserName.text.toString();
            val email = appEdtEmail.text.toString();
            val password = appEdtPassword.text.toString();
            val confirmPassword = appEdtConfirmPassword.text.toString();
            val mobile = appEdtMobile.text.toString();

            if (validation(userName, email, password, confirmPassword, mobile)) {
                presenter!!.callApiPostSignUp(SignUpRequest(userName, email, null, null, mobile, null, null, null, password, null, null));
            }
        })
    }

    override fun createPresenter(): SignUpPresenter {
        return SignUpPresenter()
    }

    override fun attachView(): SignUpView {
        return this
    }

    override fun onFailure(message: String) {
        AppUtils.showToast(this, message + "")
    }

    override fun apiGetCountryList(response: CountryResponse) {
        val dialogCountry = SpDialogCountry(this, getStringFromId(R.string.dialog_title_select_country), SpDialogCountry.OnItemClick { `object` ->
            val modelCountry = `object` as ModelCountry
            Logging.d(getToJsonClass(modelCountry))
        }, response.listCountry)
        dialogCountry.show()
    }

    override fun apiPostSignUp(response: SignUpResponse) {
        if (response.response!!.status == 200) {
            finish()
        } else if (response.response!!.status == RestConstant.conflict_409) {
            findViewById<AppCompatEditText>(R.id.appEdtPassword).setText("")
            findViewById<AppCompatEditText>(R.id.appEdtConfirmPassword).setText("")
        }

        AppUtils.showToast(this, response.response!!.message + "")

    }

    private fun validation(userName: String, email: String, password: String, confirmPassword: String, mobile: String): Boolean {
        return when {
            Validator.isEmpty(userName) -> {
                AppUtils.showToast(this, getString(R.string.msg_empty_username))
                return false
            }
            Validator.isEmpty(email) -> {
                AppUtils.showToast(this, getString(R.string.msg_empty_email))
                return false
            }
            Validator.isRegexValidator(email, Validator.patternEmail) === false -> {
                AppUtils.showToast(this, getString(R.string.msg_valid_email))
                return false
            }
            Validator.isEmpty(password) -> {
                AppUtils.showToast(this, getString(R.string.msg_empty_password))
                return false
            }
            Validator.isEmpty(confirmPassword) -> {
                AppUtils.showToast(this, getString(R.string.msg_empty_confirm_password))
                return false
            }
            Validator.isEqual(password, confirmPassword) === false -> {
                AppUtils.showToast(this, getString(R.string.msg_match_password))
                return false
            }
            Validator.isEmpty(mobile) -> {
                AppUtils.showToast(this, getString(R.string.msg_empty_mobile))
                return false
            }
            else -> return true
        }
    }

}