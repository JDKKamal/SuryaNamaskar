package com.jdkgroup.interacter.disposablemanager

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by Lakhani on 12/1/2017.
 * Managing Disposables
 * https://medium.com/@CodyEngel/managing-disposables-in-rxjava-2-for-android-388722ae1e8a
 */

object DisposableManager {

    private var compositeDisposable: CompositeDisposable? = null

    fun add(disposable: Disposable) {
        getCompositeDisposable().add(disposable)
    }

    fun dispose() {
        getCompositeDisposable().dispose()
    }

    private fun getCompositeDisposable(): CompositeDisposable {
        if (compositeDisposable == null || compositeDisposable!!.isDisposed) {
            compositeDisposable = CompositeDisposable()
            compositeDisposable!!.clear()

            //compositeDisposable.dispose();
        }
        return compositeDisposable as CompositeDisposable
    }
}