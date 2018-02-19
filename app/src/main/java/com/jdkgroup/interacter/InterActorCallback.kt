package com.jdkgroup.interacter

interface InterActorCallback<T> {

    fun onStart()

    fun onResponse(response: T)

    fun onFinish()

    fun onError(message: String)
}
