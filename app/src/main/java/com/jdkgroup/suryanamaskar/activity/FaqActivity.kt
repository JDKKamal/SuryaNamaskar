package com.jdkgroup.suryanamaskar.activity

import android.os.Bundle
import com.jdkgroup.baseclass.SimpleMVPActivity
import com.jdkgroup.model.api.faq.FaqListSectionResponse
import com.jdkgroup.presenter.FaqPresenter
import com.jdkgroup.suryanamaskar.R
import com.jdkgroup.suryanamaskar.adapter.FaqAdapter
import com.jdkgroup.utils.AppUtils
import com.jdkgroup.view.FaqView
import kotlinx.android.synthetic.main.activity_faq.*
import kotlinx.android.synthetic.main.toolbar.*

class FaqActivity : SimpleMVPActivity<FaqPresenter, FaqView>(), FaqView {
    private lateinit var faqAdapter: FaqAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faq)

        setSupportActionBar(toolBar)
        setRecyclerView(rvFaq, 0, recyclerViewLinearLayout)

        presenter!!.callApiPostFaqSection()
    }

    override fun attachView(): FaqView {
        return this
    }

    override fun apiPostFaqSectionResponse(response: FaqListSectionResponse) {
        faqAdapter = FaqAdapter(this, response.listfaqsection!!)
        rvFaq!!.adapter = faqAdapter
    }

    override fun onFailure(message: String) {
        AppUtils.showToast(this, message + "")
    }

    override fun createPresenter(): FaqPresenter {
        return FaqPresenter()
    }
}