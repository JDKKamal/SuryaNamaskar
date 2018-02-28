package com.jdkgroup.presenter

import android.os.Handler
import com.jdkgroup.baseclass.BasePresenter
import com.jdkgroup.suryanamaskar.activity.SignUpActivity
import com.jdkgroup.utils.AppUtils
import com.jdkgroup.utils.Preference
import com.jdkgroup.view.SplashScreenView

class SplashScreenPresenter : BasePresenter<SplashScreenView>() {
    fun getSplashScreenWait(timeOut: Int) {
        Handler().postDelayed({
          Preference.preferenceInstance(view!!.activity).isLogin = true

            AppUtils.startActivity(view!!.activity, SignUpActivity::class.java);
            view!!.activity.finish();

        }, timeOut.toLong())
    }
}