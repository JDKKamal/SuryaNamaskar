package com.jdkgroup.suryanamaskar.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.AppCompatButton
import android.support.v7.widget.AppCompatTextView
import android.widget.Toast
import com.jdkgroup.baseclass.SimpleMVPActivity
import com.jdkgroup.constant.RestConstant
import com.jdkgroup.customview.socialintegration.facebookintegration.FacebookLoginHelper
import com.jdkgroup.customview.socialintegration.facebookintegration.FacebookLoginListener
import com.jdkgroup.customview.socialintegration.facebookintegration.FacebookLoginModel
import com.jdkgroup.customview.socialintegration.googleintegration.GoogleLoginHelper
import com.jdkgroup.customview.socialintegration.googleintegration.GoogleLoginListener
import com.jdkgroup.customview.socialintegration.googleintegration.GoogleLoginModel
import com.jdkgroup.model.api.signup.SignUpResponse
import com.jdkgroup.model.request.SignUpRequest
import com.jdkgroup.presenter.LoginPresenter
import com.jdkgroup.suryanamaskar.DrawerActivity
import com.jdkgroup.suryanamaskar.R
import com.jdkgroup.suryanamaskar.Validator
import com.jdkgroup.utils.AppUtils
import com.jdkgroup.utils.PreferenceUtils
import com.jdkgroup.utils.logInfo
import com.jdkgroup.view.LoginView
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : SimpleMVPActivity<LoginPresenter, LoginView>(), LoginView, FacebookLoginListener, GoogleLoginListener {

    private var facebookLoginHelper: FacebookLoginHelper? = null
    private var googleLoginHelper: GoogleLoginHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        hideSoftKeyboard()

        if (PreferenceUtils.preferenceInstance(this).isLogin) {
            AppUtils.startActivity(this, DrawerActivity::class.java)
        }

        //TODO LOGIN
        findViewById<AppCompatButton>(R.id.appBtnLogin).setOnClickListener(
                {
                    val email = appEdtEmail.text.toString()
                    val password = appEdtPassword.text.toString()

                    val validator = Validator(password)
                    var result: Boolean = validator.atLeastOneNumber()
                            .nonEmpty()
                            .minimumLength(8)
                            .maximumLength(32)
                            .atLeastOneSpecialCharacter()
                            .validate()

                    logInfo(result.toString())


                    if (presenter!!.validation(email, password, this)) {
                        presenter!!.apiCall(RestConstant.CALL_API_LOGIN, SignUpRequest(email, password))
                    }
                }
        )

        findViewById<AppCompatTextView>(R.id.appTvSignUp).setOnClickListener(
                {
                    AppUtils.startActivity(this, SignUpActivity::class.java)
                }
        )

        //TODO SOCIAL LOGIN
        //facebookLoginHelper = FacebookLoginHelper(this)
        //googleLoginHelper = GoogleLoginHelper(this, this, "478402062021-u4pnl5u6cjgdla0gce49pk9rkrpktklv.apps.googleusercontent.com")
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

    //TODO API RESPONSE

    //LOGIN
    override fun apiPostLoginResponse(response: SignUpResponse) {
        if (response.response!!.status == RestConstant.ok_200) {
            PreferenceUtils.preferenceInstance(this).isLogin = true
            PreferenceUtils.preferenceInstance(this).userId = response.signup!!.userid!!
            PreferenceUtils.preferenceInstance(this).userName = response.signup!!.username!!
            PreferenceUtils.preferenceInstance(this).email = response.signup!!.email!!

            AppUtils.startActivity(this, DrawerActivity::class.java)
            finish()
        } else if (response.response!!.status == RestConstant.not_found_404) { //WHEN EMAIL NOT FOUND PASSWORD SET NULL
            appEdiTextNullSet(R.id.appEdtPassword)
        }

        AppUtils.showToast(this, response.response!!.message + "")
    }
    //FINISH API RESPONSE

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        facebookLoginHelper!!.onActivityResult(requestCode, resultCode, data)
        googleLoginHelper!!.onActivityResult(requestCode, resultCode, data)
    }

    //TODO START FACEBOOK LOGIN
    override fun onFbSignInFail(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun onFbSignInSuccess(facebookLoginModel: FacebookLoginModel) {
        println("Tag" + "Facebook login data" + getToJsonClass(facebookLoginModel))
    }

    override fun onFBSignOut() {

    }
    //FINISH FACEBOOK LOGIN

    //TODO START GOOGLE LOGIN
    override fun onGoogleAuthSignIn(googleLoginModel: GoogleLoginModel) {
        println("Tag" + "Google login data" + getToJsonClass(googleLoginModel))
    }

    override fun onGoogleAuthSignInFailed(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun onGoogleAuthSignOut() {

    }
    //FINISH GOOGLE LOGIN

    override fun onBackPressed() {
        appExist()
    }
}