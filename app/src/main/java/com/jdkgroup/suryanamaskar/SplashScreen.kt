package com.jdkgroup.suryanamaskar

import android.os.Bundle
import com.jdkgroup.baseclass.SimpleMVPActivity
import com.jdkgroup.constant.RestConstant
import com.jdkgroup.interacter.AppInteractor
import com.jdkgroup.presenter.SplashScreenPresenter
import com.jdkgroup.utils.AppUtils
import com.jdkgroup.utils.LogUtils
import com.jdkgroup.utils.PreferenceUtils
import com.jdkgroup.utils.logInfo
import com.jdkgroup.view.SplashScreenView

class SplashScreen : SimpleMVPActivity<SplashScreenPresenter, SplashScreenView>(), SplashScreenView, RestConstant {

    private val SPLASH_TIME_OUT = 3000
    private var appInteractor: AppInteractor? = null
    private var readFile : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen)

        PreferenceUtils.preferenceInstance(this).deviceToken = "bearer ac65df43b1a76c8672f3f4da2c282f822a7bf39c40b47de7af930dc21110f0f4"
        PreferenceUtils.preferenceInstance(this).isToken = false

        appInteractor = AppInteractor()
        appInteractor!!.getDeviceInfo(activity)

        if (PreferenceUtils.preferenceInstance(this).isLogin) {
            AppUtils.startActivity(this, DrawerActivity::class.java)
        } else {
            presenter!!.getSplashScreenWait(SPLASH_TIME_OUT)
        }

        //TODO READ FILE
        readFile = AppUtils.readFile("json/address", "txt", this)
        logInfo(readFile!!)
    }

    override fun createPresenter(): SplashScreenPresenter {
        return SplashScreenPresenter()
    }

    override fun attachView(): SplashScreenView {
        return this
    }

    override fun onFailure(message: String) {

    }

    override fun setSplashScreenWait() {

    }
}