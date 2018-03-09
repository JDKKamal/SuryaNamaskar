package com.jdkgroup.presenter

import android.os.Handler
import com.jdkgroup.baseclass.BasePresenter
import com.jdkgroup.suryanamaskar.activity.LoginActivity
import com.jdkgroup.utils.AppUtils
import com.jdkgroup.view.SplashScreenView

class SplashScreenPresenter : BasePresenter<SplashScreenView>() {
    fun getSplashScreenWait(timeOut: Int) {
        Handler().postDelayed({
            AppUtils.startActivity(view!!.activity, LoginActivity::class.java);
            view!!.activity.finish();

        }, timeOut.toLong())
    }
}
