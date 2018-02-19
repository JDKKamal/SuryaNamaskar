package com.jdkgroup.interacter.operators

import android.content.ActivityNotFoundException
import android.content.Context

import com.jdkgroup.suryanamaskar.R
import com.jdkgroup.interacter.InterActorCallback
import com.jdkgroup.model.api.Response

import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RxAPICallObservable {
    companion object {

        fun <T> call(context: Context, observable: Observable<T>?, callback: InterActorCallback<T>): Disposable {
            callback.onStart()

            if (observable == null) {
                throw IllegalArgumentException(context.getString(R.string.msg_observable_not_null))
            }

            if (callback == null) {
                throw IllegalArgumentException(context.getString(R.string.msg_callback_not_null))
            }

            return observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ t: T ->
                        val response = t as Response
                        when (response.status) {
                            200 -> callback.onResponse(t)

                            404 -> {
                            }

                            else -> callback.onError(response.message!!)
                        }//TODO LOGOUT
                    }) { throwable ->
                        if (throwable is SocketTimeoutException) {
                            callback.onError(context.getString(R.string.msg_connection_time_out))
                        } else if (throwable is ActivityNotFoundException) {
                            callback.onError(context.getString(R.string.msg_activity_not_found))
                        } else if (throwable is UnknownHostException || throwable is ConnectException) {
                            callback.onError(context.getString(R.string.msg_server_not_responding))
                        } else {
                            callback.onError(context.getString(R.string.msg_something_wrong))
                        }
                        callback.onFinish()
                    }
        }
    }
}
