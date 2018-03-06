package com.jdkgroup.customview.socialintegration.googleintegration

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

import com.google.android.gms.auth.GoogleAuthException
import com.google.android.gms.auth.GoogleAuthUtil
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.ResultCallback
import com.google.android.gms.common.api.Status

import java.io.IOException
import java.net.URI

class GoogleLoginHelper(private val mListener: GoogleLoginListener, private val mContext: FragmentActivity, serverClientId: String?) : GoogleApiClient.OnConnectionFailedListener {
    private val SCOPES = "oauth2:profile email"
    private val RC_SIGN_IN = 100
    private var mGoogleApiClient: GoogleApiClient? = null

    init {
        buildGoogleApiClient(buildSignInOptions(serverClientId))
    }

    private fun buildSignInOptions(serverClientId: String?): GoogleSignInOptions {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail()
        if (serverClientId != null) gso.requestIdToken(serverClientId)
        return gso.build()
    }

    private fun buildGoogleApiClient(gso: GoogleSignInOptions) {
        mGoogleApiClient = GoogleApiClient.Builder(mContext).enableAutoManage(mContext, this).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build()
    }

    fun performSignIn(activity: Activity) {
        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
        activity.startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    fun performSignIn(fragment: Fragment) {
        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
        fragment.startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == RC_SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            if (result.isSuccess) {
                val googleSignInAccount = result.signInAccount
                val task = object : AsyncTask<Void, Void, String>() {
                    override fun doInBackground(vararg params: Void): String? {
                        var token: String? = null
                        try {
                            token = GoogleAuthUtil.getToken(mContext, result.signInAccount!!.account!!, SCOPES)
                        } catch (e: IOException) {
                            e.printStackTrace()
                        } catch (e: GoogleAuthException) {
                            e.printStackTrace()
                        }

                        return token
                    }

                    override fun onPostExecute(token: String) {
                        val id: String?
                        val name: String?
                        val email: String?
                        val personPhoto: Uri?

                        id = googleSignInAccount!!.id
                        name = googleSignInAccount.displayName
                        email = googleSignInAccount.email
                        personPhoto = googleSignInAccount.photoUrl

                        val googleLoginModel = GoogleLoginModel(token, id, name, email, personPhoto)
                        mListener.onGoogleAuthSignIn(googleLoginModel)
                    }
                }
                task.execute()
            } else {
                mListener.onGoogleAuthSignInFailed(result.status.toString())
            }
        }
    }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        mListener.onGoogleAuthSignInFailed(connectionResult.errorMessage!!)
    }

    fun performSignOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback { mListener.onGoogleAuthSignOut() }
    }
}