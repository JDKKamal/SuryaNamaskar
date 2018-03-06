package com.jdkgroup.suryanamaskar.activity

import android.os.Bundle
import android.support.v7.widget.AppCompatButton
import com.jdkgroup.baseclass.SimpleMVPActivity
import com.jdkgroup.constant.RestConstant
import com.jdkgroup.customview.rximagepicker.Sources
import com.jdkgroup.model.api.signup.SignUpResponse
import com.jdkgroup.model.request.SignUpRequest
import com.jdkgroup.presenter.ProfilePresenter
import com.jdkgroup.suryanamaskar.R
import com.jdkgroup.utils.AppUtils
import com.jdkgroup.utils.ImagePicker
import com.jdkgroup.utils.Preference
import com.jdkgroup.view.ProfileView

class ProfileActivity : SimpleMVPActivity<ProfilePresenter, ProfileView>(), ProfileView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        hideSoftKeyboard()
        ImagePicker().pickImageFromSource(this, Sources.GALLERY, null, 2)

        //TODO UPDATE PROFILE
        findViewById<AppCompatButton>(R.id.appBtnSubmit).setOnClickListener(
                {
                    val username = appEdiTextGetString(R.id.appEdtUserName)
                    val mobile = appEdiTextGetString(R.id.appEdtMobile)
                    val pincode = appEdiTextGetString(R.id.appEdtPinCode)
                    val address = appEdiTextGetString(R.id.appEdtAddress)

                    presenter!!.callApiPostProfile(SignUpRequest(Preference.preferenceInstance(this).userId, username,  Preference.preferenceInstance(this).email, "kamal", 1, mobile, "0", "0", pincode, address));
                }
        )
    }

    override fun createPresenter(): ProfilePresenter {
        return ProfilePresenter()
    }

    override fun attachView(): ProfileView {
        return this
    }

    override fun onFailure(message: String) {
        AppUtils.showToast(this, message + "")
    }

    //TODO API RESPONSE

    //PROFILE
    override fun apiPostProfileResponse(response: SignUpResponse) {
        if (response.response!!.status == RestConstant.ok_200) {
            Preference.preferenceInstance(this).userId = response.signup!!.userid!!
            Preference.preferenceInstance(this).userName = response.signup!!.username!!
            Preference.preferenceInstance(this).email = response.signup!!.email!!
        }
        AppUtils.showToast(this, response.response!!.message + "")
    }
    //FINISH API RESPONSE

    override fun onBackPressed() {
        finish()
    }
}