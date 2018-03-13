package com.jdkgroup.suryanamaskar.activity

import android.Manifest
import android.os.Bundle
import android.support.v7.widget.AppCompatButton
import com.jdkgroup.baseclass.SimpleMVPActivity
import com.jdkgroup.constant.RestConstant
import com.jdkgroup.model.api.signup.SignUpResponse
import com.jdkgroup.model.request.SignUpRequest
import com.jdkgroup.presenter.ProfilePresenter
import com.jdkgroup.suryanamaskar.R
import com.jdkgroup.utils.AppUtils
import com.jdkgroup.utils.PreferenceUtils
import com.jdkgroup.view.ProfileView
import com.jdkgroup.customview.permission.askPermissions
import com.jdkgroup.customview.permission.handlePermissionsResult
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : SimpleMVPActivity<ProfilePresenter, ProfileView>(), ProfileView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        hideSoftKeyboard()

        permission()
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
            PreferenceUtils.preferenceInstance(this).userId = response.signup!!.userid!!
            PreferenceUtils.preferenceInstance(this).userName = response.signup!!.username!!
            PreferenceUtils.preferenceInstance(this).email = response.signup!!.email!!
        }
        AppUtils.showToast(this, response.response!!.message + "")
    }
    //FINISH API RESPONSE

    override fun onBackPressed() {
        finish()
    }

    private fun permission() {
        askPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE) {
            onGranted {
                //TODO UPDATE PROFILE
                findViewById<AppCompatButton>(R.id.appBtnSubmit).setOnClickListener(
                        {
                            var username = appEdtUserName.text.toString()
                            var mobile = appEdtMobile.text.toString()
                            var pincode = appEdtPinCode.text.toString()
                            var address = appEdtAddress.text.toString()

                            presenter!!.callApiPostProfile(SignUpRequest(PreferenceUtils.preferenceInstance(activity).userId, username, PreferenceUtils.preferenceInstance(activity).email, "kamal", 1, mobile, "0", "0", pincode, address));
                        }
                )
            }

            onDenied {
                it.forEach {
                    when (it) {
                        Manifest.permission.CALL_PHONE ->  AppUtils.showToast(activity, "Call Phone is denied")
                        Manifest.permission.READ_SMS ->AppUtils.showToast(activity, "Read Sms is denied")
                    }
                }
            }

            onShowRationale { request ->
                var permissions = ""
                request.permissions.forEach {

                    permissions += when (it) {
                        Manifest.permission.CALL_PHONE -> " Call Phone"
                        Manifest.permission.READ_SMS -> " Read Sms"
                        else -> ""
                    }

                }

                snack("You should grant permission for $permissions") {
                    request.retry()
                }
            }

            onNeverAskAgain {
                it.forEach {
                    when (it) {
                        Manifest.permission.CALL_PHONE ->  AppUtils.showToast(activity, "Never ask again for Call Phone")
                        Manifest.permission.READ_SMS -> AppUtils.showToast(activity, "Never ask again for Read Sms")
                    }
                }
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        handlePermissionsResult(requestCode, permissions, grantResults)
    }

    private fun snack(message: String, action: () -> Unit = {}) {

    }
}