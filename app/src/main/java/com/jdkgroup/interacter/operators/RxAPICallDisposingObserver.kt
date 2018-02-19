package com.jdkgroup.interacter.operators

import android.content.ActivityNotFoundException
import android.content.Context
import android.support.annotation.CallSuper

import com.jdkgroup.suryanamaskar.R
import com.jdkgroup.interacter.InterActorCallback
import com.jdkgroup.interacter.disposablemanager.DisposableManager

import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Created by Lakhani on 12/1/2017.
 * https://medium.com/@CodyEngel/managing-disposables-in-rxjava-2-for-android-388722ae1e8a
 */

class RxAPICallDisposingObserver<T>(private val context: Context, private val callback: InterActorCallback<T>) : Observer<T> {

    @CallSuper
    override fun onSubscribe(d: Disposable) {
        callback.onStart()
        DisposableManager.add(d)
    }

    override fun onNext(t: T) {
        callback.onResponse(t)
    }

    override fun onError(e: Throwable) {
        callback.onError(e.message.toString())
        callback.onFinish()
    }

    override fun onComplete() {
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