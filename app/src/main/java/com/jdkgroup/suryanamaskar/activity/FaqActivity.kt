package com.jdkgroup.suryanamaskar.activity

import android.os.Bundle
import com.jdkgroup.baseclass.SimpleMVPActivity
import com.jdkgroup.model.api.faq.FaqListSectionResponse
import com.jdkgroup.presenter.FaqPresenter
import com.jdkgroup.suryanamaskar.R
import com.jdkgroup.utils.AppUtils
import com.jdkgroup.utils.logInfo
import com.jdkgroup.view.FaqView

class FaqActivity : SimpleMVPActivity<FaqPresenter, FaqView>(), FaqView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        presenter!!.callApiPostFaqSection()
    }

    override fun attachView(): FaqView {
        return this
    }

    override fun apiPostFaqSectionResponse(response: FaqListSectionResponse) {
        logInfo(getToJsonClass(response))
    }

    override fun onFailure(message: String) {
        AppUtils.showToast(this, message + "")
    }

    override fun createPresenter(): FaqPresenter {
        return FaqPresenter()
    }
}