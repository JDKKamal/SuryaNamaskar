package com.jdkgroup.baseclass

//TODO DEVELOPED BY KAMLESH LAKHANI
/*
  * P - PRESENTER
  * V - VIEW
  * EXTENDS BASEFRAGMENT
  * THAT CLASS USE EXTEND fragment, WHEN PROGRAMMER LOGIC, API CALL, DATABASE etc. WRITE CODE IN AppInteractor.java class.
  * MVPFragment<P, V> implements V
*/

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class MVPFragment<P : BasePresenter<V>, V : BaseView> : BaseFragment() {

    var presenter: P? = null
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
        presenter!!.attachView(attachView())
    }

    abstract fun createPresenter(): P

    abstract fun attachView(): V

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun hasInternet(): Boolean {
        return isInternet
    }
}
