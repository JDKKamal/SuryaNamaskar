package com.jdkgroup.baseclass

//TODO DEVELOPED BY KAMLESH LAKHANI
/*
  * THAT CLASS USE EXTEND Activity OR AppCompatActivity, WHEN PROGRAMMER LOGIC, API CALL, DATABASE etc. WRITE CODE IN AppInteractor.java class.
  * SimpleMVPActivity<P, V> implements V
*/

import android.app.Activity
import android.os.Bundle

abstract class SimpleMVPActivity<P : BasePresenter<V>, V : BaseView> : BaseActivity() {
    var presenter: P? = null
        private set

    override val activity: Activity
        get() = this


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = createPresenter()
        presenter!!.attachView(attachView())
    }

    public override fun onDestroy() {
        super.onDestroy()
    }

    abstract fun createPresenter(): P

    abstract fun attachView(): V

    fun hasInternet(): Boolean {
        return isInternet
    }
}
