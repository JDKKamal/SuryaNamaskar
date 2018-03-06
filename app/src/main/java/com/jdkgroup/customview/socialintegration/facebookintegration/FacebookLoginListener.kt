package com.jdkgroup.customview.socialintegration.facebookintegration

interface FacebookLoginListener {
    fun onFbSignInFail(errorMessage: String)
    fun onFbSignInSuccess(facebookLoginModel: FacebookLoginModel)
    fun onFBSignOut()
}
