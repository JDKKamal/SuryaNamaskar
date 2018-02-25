package com.jdkgroup.suryanamaskar.activity

import android.os.Bundle
import com.jdkgroup.baseclass.SimpleMVPActivity
import com.jdkgroup.model.api.Response
import com.jdkgroup.model.api.countrylist.CountryResponse
import com.jdkgroup.model.api.countrylist.ModelCountry
import com.jdkgroup.model.api.signup.SignUpResponse
import com.jdkgroup.model.request.SignUpRequest
import com.jdkgroup.presenter.SignUpPresenter
import com.jdkgroup.suryanamaskar.R
import com.jdkgroup.suryanamaskar.dialog.SpDialogCountry
import com.jdkgroup.utils.AppUtils
import com.jdkgroup.utils.Logging
import com.jdkgroup.view.SignUpView

class SignUpActivity : SimpleMVPActivity<SignUpPresenter, SignUpView>(), SignUpView {
    private var listCountry: List<ModelCountry>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_activity)

        hideSoftKeyboard()

        presenter!!.callApiGetCountryList()
        presenter!!.callApiPostSignUp(SignUpRequest("username", "email", "country", "state", "mobile", "pincode", "gg", "gg", "password", 1, 0));
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
        if (response.response!!.status == 200)
        {

        }
        else
        {
            AppUtils.showToast(this, response.response!!.message + "")
        }
    }

}