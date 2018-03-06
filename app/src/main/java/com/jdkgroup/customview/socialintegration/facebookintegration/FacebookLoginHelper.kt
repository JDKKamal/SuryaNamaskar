package com.jdkgroup.customview.socialintegration.facebookintegration

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.annotation.CheckResult
import android.support.v4.app.Fragment

import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.GraphResponse
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult

import org.json.JSONObject

import java.util.Arrays

class FacebookLoginHelper(private val mListener: FacebookLoginListener) {
    @get:CheckResult
    val callbackManager: CallbackManager

    init {
        callbackManager = CallbackManager.Factory.create()
        val mCallBack = object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                val request = GraphRequest.newMeRequest(
                        loginResult.accessToken
                ) { `object`, response ->
                    try {
                        val name: String
                        val firsName: String
                        val lastName: String
                        val gender: String
                        val email: String?
                        val profilePicture: String
                        val cellNo: String?
                        name = `object`.getString("name")
                        firsName = `object`.getString("first_name")
                        lastName = `object`.getString("last_name")
                        gender = `object`.getString("gender")
                        profilePicture = "https://graph.facebook.com/" + loginResult.accessToken.userId + "/picture?type=large"
                        if (`object`.has("email")) {
                            email = `object`.getString("email")
                        } else {
                            email = null
                        }
                        if (`object`.has("phone")) {
                            cellNo = `object`.getString("phone")
                        } else {
                            cellNo = null
                        }

                        val facebookLoginModel = FacebookLoginModel(loginResult.accessToken.token, loginResult.accessToken.userId, name, firsName, lastName, gender, email, profilePicture, cellNo)
                        mListener.onFbSignInSuccess(facebookLoginModel)
                    } catch (ex: Exception) {

                    }
                }
                val parameters = Bundle()
                parameters.putString("fields", "name,first_name,last_name,gender,email,picture.type(large),")
                request.parameters = parameters
                request.executeAsync()
            }

            override fun onCancel() {
                mListener.onFbSignInFail("User cancelled operation")
            }

            override fun onError(e: FacebookException) {
                mListener.onFbSignInFail(e.message!!)
            }
        }
        LoginManager.getInstance().registerCallback(callbackManager, mCallBack)
    }

    fun performSignIn(activity: Activity) {
        LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("public_profile", "user_friends", "email"))
    }

    fun performSignIn(fragment: Fragment) {
        LoginManager.getInstance().logInWithReadPermissions(fragment, Arrays.asList("public_profile", "user_friends", "email"))
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    fun performSignOut() {
        LoginManager.getInstance().logOut()
        mListener.onFBSignOut()
    }
}
