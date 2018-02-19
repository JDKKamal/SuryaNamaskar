package com.jdkgroup.utils

import android.util.Log

object Logging {
    private val tag = "Tag"

    var isDebugLogging = true

    fun v(msg: String) {
        if (isDebugLogging) {
            Log.v(tag, msg)
        }
    }

    fun v(msg: String, tr: Throwable) {
        if (isDebugLogging) {
            Log.v(tag, msg, tr)
        }
    }

    fun d(msg: String) {
        if (isDebugLogging) {
            Log.d(tag, msg)
        }
    }

    fun d(msg: String, tr: Throwable) {
        if (isDebugLogging) {
            Log.d(tag, msg, tr)
        }
    }

    fun i(msg: String) {
        if (isDebugLogging) {
            Log.i(tag, msg)
        }
    }

    fun i(msg: String, tr: Throwable) {
        if (isDebugLogging) {
            Log.i(tag, msg, tr)
        }
    }

    fun w(msg: String) {
        if (isDebugLogging) {
            Log.w(tag, msg)
        }
    }

    fun w(msg: String, tr: Throwable) {
        if (isDebugLogging) {
            Log.w(tag, msg, tr)
        }
    }

    fun w(tr: Throwable) {
        if (isDebugLogging) {
            Log.w(tag, tr)
        }
    }

    fun e(msg: String) {
        if (isDebugLogging) {
            Log.e(tag, msg)
        }
    }

    fun e(msg: String, tr: Throwable) {
        if (isDebugLogging) {
            Log.e(tag, msg, tr)
        }
    }
}