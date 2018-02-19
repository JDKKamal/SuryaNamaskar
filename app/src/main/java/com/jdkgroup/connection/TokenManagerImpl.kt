package com.jdkgroup.connection

import android.content.Context
import com.jdkgroup.utils.Preference

class TokenManagerImpl(private val context: Context) : TokenManager {

    override//TOKEN SET bearer ac65df43b1a76c8672f3f4da2c282f822a7bf39c40b47de7af930dc21110f0f4
    val token: String
        get() = Preference.preferenceInstance(context).deviceToken

    override fun hasToken(): Boolean {
        //TOKEN CHECK
        return if (Preference.preferenceInstance(context).isToken) {
            true
        } else {
            false
        }
    }

    override fun clearToken() {
        //TOKEN CLEAR
        Preference.preferenceInstance(context).deviceToken = ""
    }

    @Synchronized
    override fun refreshToken() {
        //TOKEN REFRESH
    }
}
