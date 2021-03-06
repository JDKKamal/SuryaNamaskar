package com.jdkgroup.presenter

import android.app.Activity
import android.os.Handler
import com.jdkgroup.baseclass.BasePresenter
import com.jdkgroup.suryanamaskar.activity.FaqActivity
import com.jdkgroup.utils.AppUtils
import com.jdkgroup.view.SplashScreenView

class SplashScreenPresenter : BasePresenter<SplashScreenView>() {
    fun getSplashScreenWait(activity: Activity, timeOut: Int) {
        Handler().postDelayed({
            AppUtils.startActivity(activity, FaqActivity::class.java);
            activity.finish();

        }, timeOut.toLong())
    }
}
