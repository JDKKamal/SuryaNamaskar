package com.jdkgroup.baseclass

//TODO DEVELOPED BY KAMLESH LAKHANI

import com.jdkgroup.interacter.AppInteractor

open class BasePresenter<V : BaseView> {
    var view: V? = null
        private set

    val isViewAttached: Boolean
        get() = view != null

    protected val appInteractor: AppInteractor
        get() = AppInteractor()

    internal fun attachView(view: V) {
        this.view = view
    }

    fun hasInternet(): Boolean {
        return view!!.hasInternet()
    }

    fun checkViewAttached() {
        if (!isViewAttached) throw MvpViewNotAttachedException()
    }

    class MvpViewNotAttachedException : RuntimeException("Please call Presenter.attachView(MvpView) before requesting data to the Presenter")
}
