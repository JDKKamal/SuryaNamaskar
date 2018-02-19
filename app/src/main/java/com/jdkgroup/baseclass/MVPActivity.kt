package com.jdkgroup.baseclass

//TODO DEVELOPED BY KAMLESH LAKHANI
/*
   * P - PRESENTER
   * V - VIEW
   * EXTENDS BASEACTIVITY
* */

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.Toolbar

import com.jdkgroup.suryanamaskar.R

import butterknife.BindView

abstract class MVPActivity<P : BasePresenter<V>, V : BaseView> : BaseActivity() {

    @BindView(R.id.toolBar)
    var toolBar: Toolbar? = null

    var presenter: P? = null
        private set

    override val activity: Activity
        get() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
        presenter!!.attachView(attachView())

        setSupportActionBar(toolBar)
    }

    public override fun onDestroy() {
        super.onDestroy()
    }

    abstract fun createPresenter(): P

    abstract fun attachView(): V
}
