package com.jdkgroup.utils

import android.content.Context
import android.content.SharedPreferences

import com.jdkgroup.constant.AppConstant

import android.content.Context.MODE_PRIVATE

open class Preference private constructor(private val mContext: Context) : AppConstant {
    private val sharedPreferences: SharedPreferences

    companion object {
        private val SP_NAME = "suryanamskar"
        private var preference: Preference? = null

        fun preferenceInstance(mContext: Context): Preference {
            return Preference(mContext)
        }

        private fun removeInstance() {
            preference = null
        }
    }

    init {
        sharedPreferences = mContext.getSharedPreferences(SP_NAME, MODE_PRIVATE)
    }

    fun clearAllPrefs() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
        removeInstance()
    }

    var isLogin: Boolean
        get() = sharedPreferences.getBoolean(AppConstant.Companion.SP_IS_LOGIN, false)
        set(islogin) = sharedPreferences.edit().putBoolean(AppConstant.Companion.SP_IS_LOGIN, islogin).apply()

    var isToken: Boolean
        get() = sharedPreferences.getBoolean(AppConstant.Companion.SP_IS_TOKEN, false)
        set(islogin) = sharedPreferences.edit().putBoolean(AppConstant.Companion.SP_IS_TOKEN, islogin).apply()

    var deviceToken: String
        get() = sharedPreferences.getString(AppConstant.Companion.SP_DEVICE_TOKEN, "")
        set(deviceToken) = sharedPreferences.edit().putString(AppConstant.Companion.SP_DEVICE_TOKEN, deviceToken).apply()

    var authToken: String
        get() = sharedPreferences.getString(AppConstant.Companion.SP_AUTH_TOKEN, "")
        set(deviceToken) = sharedPreferences.edit().putString(AppConstant.Companion.SP_AUTH_TOKEN, deviceToken).apply()

    var deviceId: String
        get() = sharedPreferences.getString(AppConstant.Companion.SP_DEVICE_ID, "")
        set(deviceId) = sharedPreferences.edit().putString(AppConstant.Companion.SP_DEVICE_ID, deviceId).apply()

    var userId: String
        get() = sharedPreferences.getString(AppConstant.Companion.SP_USER_ID, "")
        set(userId) = sharedPreferences.edit().putString(AppConstant.Companion.SP_USER_ID, userId).apply()

    var userName: String
        get() = sharedPreferences.getString(AppConstant.Companion.SP_USERNAME, "")
        set(userName) = sharedPreferences.edit().putString(AppConstant.Companion.SP_USERNAME, userName).apply()

    var email: String
        get() = sharedPreferences.getString(AppConstant.Companion.SP_EMAIL, "")
        set(email) = sharedPreferences.edit().putString(AppConstant.Companion.SP_EMAIL, email).apply()

    var loginStatus: Int
        get() = sharedPreferences.getInt(AppConstant.Companion.SP_LOGIN_STATUS, 0)
        set(loginStatus) = sharedPreferences.edit().putInt(AppConstant.Companion.SP_LOGIN_STATUS, loginStatus).apply()

    var mobile: String
        get() = sharedPreferences.getString(AppConstant.Companion.SP_MOBILE, "")
        set(mobile) = sharedPreferences.edit().putString(AppConstant.Companion.SP_MOBILE, mobile).apply()

    var socialId: String
        get() = sharedPreferences.getString(AppConstant.Companion.SP_SOCIAL_ID, "")
        set(socialId) = sharedPreferences.edit().putString(AppConstant.Companion.SP_SOCIAL_ID, socialId).apply()

    var settingSignUp: Int
        get() = sharedPreferences.getInt(AppConstant.Companion.SP_SETTING_SIGNUP, 0)
        set(signUp) = sharedPreferences.edit().putInt(AppConstant.Companion.SP_SETTING_SIGNUP, signUp).apply()

    var settingLogin: Int
        get() = sharedPreferences.getInt(AppConstant.Companion.SP_SETTING_LOGIN, 0)
        set(login) = sharedPreferences.edit().putInt(AppConstant.Companion.SP_SETTING_LOGIN, login).apply()

    var settingUsername: Int
        get() = sharedPreferences.getInt(AppConstant.Companion.SP_SETTING_USERNAME, 0)
        set(username) = sharedPreferences.edit().putInt(AppConstant.Companion.SP_SETTING_USERNAME, username).apply()

    var settingEmail: Int
        get() = sharedPreferences.getInt(AppConstant.Companion.SP_SETTING_EMAIL, 0)
        set(email) = sharedPreferences.edit().putInt(AppConstant.Companion.SP_SETTING_EMAIL, email).apply()

    var settingMobile: Int
        get() = sharedPreferences.getInt(AppConstant.Companion.SP_SETTING_MOBILE, 0)
        set(mobile) = sharedPreferences.edit().putInt(AppConstant.Companion.SP_SETTING_MOBILE, mobile).apply()

    var settingProfilePicture: Int
        get() = sharedPreferences.getInt(AppConstant.Companion.SP_SETTING_PROFILE_PICTURE, 0)
        set(profilePicture) = sharedPreferences.edit().putInt(AppConstant.Companion.SP_SETTING_PROFILE_PICTURE, profilePicture).apply()

    var settingPassword: Int
        get() = sharedPreferences.getInt(AppConstant.Companion.SP_SETTING_PASSWORD, 0)
        set(password) = sharedPreferences.edit().putInt(AppConstant.Companion.SP_SETTING_PASSWORD, password).apply()

    var appLoad: Int
        get() = sharedPreferences.getInt(AppConstant.Companion.SP_APP_LOAD, 0)
        set(password) = sharedPreferences.edit().putInt(AppConstant.Companion.SP_APP_LOAD, password).apply()

    var fcmToken: String
        get() = sharedPreferences.getString(AppConstant.Companion.SP_FCM_TOKEN, "")
        set(fcmToken) = sharedPreferences.edit().putString(AppConstant.Companion.SP_FCM_TOKEN, fcmToken).apply()


}
