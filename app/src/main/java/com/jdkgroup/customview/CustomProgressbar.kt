package com.jdkgroup.customview

import android.app.Dialog
import android.content.Context

import com.jdkgroup.suryanamaskar.R

class CustomProgressbar(private val context: Context) {
    private val dialog: Dialog?

    val isShowing: Boolean?
        get() = dialog!!.isShowing

    init {
        dialog = Dialog(context, R.style.CircularProgressTransparent)
    }

    fun show(isCancelable: Boolean?) {
        dialog!!.setCancelable(isCancelable!!)
        dialog.setContentView(R.layout.progressbar_dialog)
        dialog.show()
    }

    fun hide() {
        if (dialog != null) {
            dialog.cancel()
            dialog.dismiss()
        }
    }
}
