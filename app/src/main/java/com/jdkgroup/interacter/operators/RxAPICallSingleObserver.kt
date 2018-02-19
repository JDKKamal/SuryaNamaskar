package com.jdkgroup.interacter.operators

import android.content.ActivityNotFoundException
import android.content.Context
import android.support.annotation.CallSuper

import com.jdkgroup.suryanamaskar.R
import com.jdkgroup.interacter.InterActorCallback
import com.jdkgroup.interacter.disposablemanager.DisposableManager
import com.jdkgroup.model.api.Response

import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

/**
 * Created by Lakhani on 12/1/2017.
 * https://medium.com/@CodyEngel/managing-disposables-in-rxjava-2-for-android-388722ae1e8a
 */

class RxAPICallSingleObserver<T>(private val context: Context, private val callback: InterActorCallback<T>) : SingleObserver<T> {

    @CallSuper
    override fun onSubscribe(d: Disposable) {
        callback.onStart()
        DisposableManager.add(d)
    }

    override fun onSuccess(t: T) {
        val response = t as Response
        when (response.status) {
            200 -> callback.onResponse(t)

            404 -> {
            }

            else -> callback.onError(response.message!!)
        }//TODO LOGOUT
    }


    override fun onError(e: Throwable) {
        callback.onError(exceptionHandle(e))
        callback.onFinish()
    }


    private fun exceptionHandle(throwable: Throwable): String {
        return if (throwable is SocketTimeoutException) {
            context.getString(R.string.msg_connection_time_out)
        } else if (throwable is ActivityNotFoundException) {
            context.getString(R.string.msg_activity_not_found)
        } else if (throwable is UnknownHostException || throwable is ConnectException) {
            context.getString(R.string.msg_server_not_responding)
        } else {
            context.getString(R.string.msg_something_wrong)
        }
    }
}