package com.jdkgroup.suryanamaskar

import android.os.Bundle
import android.support.v7.widget.AppCompatEditText
import android.view.View
import android.widget.RelativeLayout

import com.jdkgroup.baseclass.SimpleMVPActivity
import com.jdkgroup.model.api.countrylist.CountryResponse
import com.jdkgroup.model.api.countrylist.ModelCountry
import com.jdkgroup.presenter.SignUpPresenter
import com.jdkgroup.view.SignUpView

import butterknife.BindView
import butterknife.OnClick
import com.jdkgroup.utils.AppUtils

class SignUpActivity : SimpleMVPActivity<SignUpPresenter, SignUpView>(), SignUpView {

    @BindView(R.id.appEdtCountry)
    internal var appEdtCountry: AppCompatEditText? = null
    @BindView(R.id.llCountry)
    internal var llCountry: RelativeLayout? = null
    @BindView(R.id.llState)
    internal var llState: RelativeLayout? = null

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
        AppUtils.showToast(this, message)
    }

    override fun apiGetCountryList(response: CountryResponse) {
        val listCountry = response.listCountry

        println("Tag" + getToJson(listCountry!!))
    }

    @OnClick(R.id.appEdtCountry, R.id.llState)
    fun onViewClicked(view: View) {
        when (view.id) {
            R.id.appEdtCountry -> {
            }
            R.id.llState -> {
            }
        }
    }
}
