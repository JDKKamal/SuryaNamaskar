package com.jdkgroup.interacter

//TODO DEVELOPED BY KAMLESH LAKHANI
/*
 * TODO COMMON LOGIC API CALL, DATABASE ETC.
 */

import android.app.Activity
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import android.telephony.TelephonyManager
import android.util.Base64
import com.jdkgroup.connection.RestClient

import com.jdkgroup.constant.RestConstant
import com.jdkgroup.interacter.operators.RxAPICallDisposingObserver
import com.jdkgroup.model.ModelOSInfo
import com.jdkgroup.model.api.countrylist.CountryResponse
import com.jdkgroup.model.api.signup.SignUpResponse
import com.jdkgroup.model.request.SignUpRequest

import java.security.MessageDigest
import java.util.ArrayList

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import com.jdkgroup.model.api.Response
import com.jdkgroup.utils.logInfo
import okhttp3.MultipartBody

class AppInteractor : RestConstant {

    //TODO DEFAULT
    fun getDeviceInfo(activity: Activity): List<ModelOSInfo> {
        val packageInfo: PackageInfo
        val deviceUniqueId: String
        val deviceType: String
        val deviceName: String
        val osVersion: String
        var appVersion = ""
        val countryIso: String
        val networkOperatorName: String

        deviceUniqueId = Settings.Secure.getString(activity.contentResolver, Settings.Secure.ANDROID_ID)
        deviceType = "Android"
        deviceName = Build.BRAND + Build.MODEL
        osVersion = Build.VERSION.RELEASE

        try {
            packageInfo = activity.packageManager.getPackageInfo(activity.packageName, 0)
            appVersion = packageInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }

        val tm = activity.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        countryIso = tm.networkCountryIso
        networkOperatorName = tm.networkOperatorName

        val alModelOSInfo = ArrayList<ModelOSInfo>()
        alModelOSInfo.add(ModelOSInfo(deviceUniqueId, deviceType, deviceName, osVersion, appVersion, countryIso, networkOperatorName))

        return alModelOSInfo
    }

    //FACEBOOK KEY
    fun getFacebookHashKey(activity: Activity, packageName: String) {
        val info: PackageInfo
        try {
            info = activity.packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                val md: MessageDigest
                md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                val facebookKeyBase64 = String(Base64.encode(md.digest(), 0))
                logInfo(facebookKeyBase64)
                //String facebookkeyBase64new = new String(Base64.encodeBytes(md.digest()));
            }
        } catch (e: Exception) {

        }

    }

    //TODO CALL API
    fun apiGetCountryList(context: Context, callback: InterActorCallback<CountryResponse>) {
        RestClient(context).service.apiGetCountryList(RestConstant.Companion.BASE_URL + RestConstant.Companion.API_GET_COUNTRY_LIST)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(RxAPICallDisposingObserver(context, callback))
    }

    fun apiPostSignUp(context: Context, signUpRequest: SignUpRequest, callback: InterActorCallback<SignUpResponse>) {
        RestClient(context).service.apiPostSignUp(RestConstant.Companion.BASE_URL + RestConstant.Companion.API_POST_SIGN_UP, signUpRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(RxAPICallDisposingObserver(context, callback))
    }

    fun apiPostLogin(context: Context, signUpRequest: SignUpRequest, callback: InterActorCallback<SignUpResponse>) {
        RestClient(context).service.apiPostLogin(RestConstant.Companion.BASE_URL + RestConstant.Companion.API_POST_LOGIN, signUpRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(RxAPICallDisposingObserver(context, callback))
    }

    fun apiPostProfile(context: Context, signUpRequest: SignUpRequest, callback: InterActorCallback<SignUpResponse>) {
        RestClient(context).service.apiPostProfile(RestConstant.Companion.BASE_URL + RestConstant.Companion.API_POST_PROFILE, signUpRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(RxAPICallDisposingObserver(context, callback))
    }

    fun apiMultiPartProfilePicture(context: Context, file: MultipartBody.Part, callback: InterActorCallback<Response>) {
        RestClient(context).service.apiMultiPartProfilePicture(RestConstant.Companion.BASE_URL + RestConstant.Companion.API_MULTIPART_UPLOAD_USER_PROFILE_PICTURE, file)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(RxAPICallDisposingObserver(context, callback))
    }

    /*private fun toSubscribe(rxAPICallDisposingObserver: RxAPICallDisposingObserver<Any>) {
        observable!!.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(rxAPICallDisposingObserver)
    }*/
}