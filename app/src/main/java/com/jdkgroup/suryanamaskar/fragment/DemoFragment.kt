package com.jdkgroup.suryanamaskar.fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jdkgroup.baseclass.MVPFragment
import com.jdkgroup.model.api.signup.SignUpResponse
import com.jdkgroup.presenter.LoginPresenter
import com.jdkgroup.suryanamaskar.R
import com.jdkgroup.view.LoginView

class DemoFragment : MVPFragment<LoginPresenter, LoginView>(), LoginView {

    override fun createPresenter(): LoginPresenter {
        return LoginPresenter()
    }

    override fun attachView(): LoginView {
        return this
    }

    override fun apiPostLoginResponse(response: SignUpResponse) {

    }

    override fun onFailure(message: String) {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_login, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
}