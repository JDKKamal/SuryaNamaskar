package com.jdkgroup.customview.socialintegration.googleintegration

interface GoogleLoginListener {
    fun onGoogleAuthSignIn(googleLoginModel: GoogleLoginModel)
    fun onGoogleAuthSignInFailed(errorMessage: String)
    fun onGoogleAuthSignOut()
}
