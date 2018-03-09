package com.jdkgroup.baseclass

//TODO DEVELOPED BY KAMLESH LAKHANI
/* BASEAPPLICATION
   *  SET MULTIDEX, LOOGING, CALLINGGRAPHY (FONT)
   *  REALM (DATABASE)
*/

import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication

import com.jdkgroup.suryanamaskar.R
import com.jdkgroup.utils.LogLevel
import com.jdkgroup.utils.LogUtils

import uk.co.chrisjenx.calligraphy.CalligraphyConfig

class BaseApplication : MultiDexApplication() {
    var baseApplication: BaseApplication? = null

    override fun onCreate() {
        super.onCreate()
        LogUtils.addLevel(LogLevel.ALL)
        baseApplication = this

        MultiDex.install(this)

        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath(this.getString(R.string.regular_font))
                .setFontAttrId(R.attr.fontPath)
                .build())
    }

   /* companion object {
        var baseApplication: BaseApplication? = null
            private set
    }*/
}