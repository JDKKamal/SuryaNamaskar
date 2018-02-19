package com.jdkgroup.baseclass

//TODO DEVELOPED BY KAMLESH LAKHANI
/* BASEAPPLICATION
   *  SET MULTIDEX, LOOGING, CALLINGGRAPHY (FONT)
   *  REALM (DATABASE)
*/

import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication

import com.facebook.stetho.Stetho
import com.jdkgroup.suryanamaskar.R
import com.jdkgroup.utils.Logging

import uk.co.chrisjenx.calligraphy.CalligraphyConfig

class BaseApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        baseApplication = this

        MultiDex.install(this)
        Logging.isDebugLogging = Logging.isDebugLogging

        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath(this.getString(R.string.regular_font))
                .setFontAttrId(R.attr.fontPath)
                .build())
    }

    companion object {
        var baseApplication: BaseApplication? = null
            private set
    }
}