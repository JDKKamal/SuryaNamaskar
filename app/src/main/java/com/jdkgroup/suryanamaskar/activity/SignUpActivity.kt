package com.jdkgroup.suryanamaskar.activity

import android.os.Bundle
import com.jdkgroup.baseclass.SimpleMVPActivity
import com.jdkgroup.model.api.countrylist.CountryResponse
import com.jdkgroup.model.api.countrylist.ModelCountry
import com.jdkgroup.presenter.SignUpPresenter
import com.jdkgroup.suryanamaskar.R
import com.jdkgroup.suryanamaskar.dialog.SpDialogCountry
import com.jdkgroup.utils.Logging
import com.jdkgroup.view.SignUpView

class SignUpActivity : SimpleMVPActivity<SignUpPresenter, SignUpView>(), SignUpView{

    private var listCountry: List<ModelCountry>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_activity)

        hideSoftKeyboard()
        bindViews()

        presenter!!.callApiGetCountryList()
    }

    override fun createPresenter(): SignUpPresenter {
        return SignUpPresenter()
    }

    override fun attachView(): SignUpView {
        return this
    }

    override fun onFailure(message: String) {

    }

    override fun apiGetCountryList(response: CountryResponse) {
        val dialogCountry = SpDialogCountry(this, getStringFromId(R.string.dialog_title_select_country), SpDialogCountry.OnItemClick { `object` ->
            val modelCountry = `object` as ModelCountry
            Logging.d(getToJsonClass(modelCountry))
        }, response.listCountry)
        dialogCountry.show()
    }

}