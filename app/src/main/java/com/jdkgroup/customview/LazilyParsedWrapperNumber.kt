package com.jdkgroup.customview

import java.math.BigDecimal

/**
 * Created by kamlesh on 8/18/2017.
 */

class LazilyParsedWrapperNumber(private val value: String) {

    fun intValue(): Int {
        try {
            return Integer.parseInt(value)
        } catch (e: NumberFormatException) {
            try {
                return java.lang.Long.parseLong(value).toInt()
            } catch (nfe: NumberFormatException) {
                return BigDecimal(value).toInt()
            }

        }

    }

    fun longValue(): Long {
        try {
            return java.lang.Long.parseLong(value)
        } catch (e: NumberFormatException) {
            return BigDecimal(value).toLong()
        }

    }

    fun floatValue(): Float {
        return java.lang.Float.parseFloat(value)
    }

    fun doubleValue(): Double {
        return java.lang.Double.parseDouble(value)
    }

}
