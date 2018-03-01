package com.jdkgroup.utils

import com.google.common.base.CharMatcher
import com.google.common.base.Strings

import org.apache.commons.lang3.StringUtils
import org.apache.commons.validator.routines.RegexValidator

import java.util.regex.Matcher
import java.util.regex.Pattern

object Validator {

    var patternPassword = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$"
    var patternBusinessName = "^[a-zA-Z0-9]+$"
    var patternEmail = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z]{2,8}" +
            ")+"
    val patternName = "[a-zA-Z]{3,10}$"
    val patternMobile = "^[0-9]{10,10}$"

    fun isEmpty(str: String): Boolean {
        return StringUtils.isEmpty(str) || StringUtils.isBlank(str) || Strings.isNullOrEmpty(str)
    }

    fun isRegexValidator(str: String, expression: String): Boolean {
        val sensitive = RegexValidator(expression)
        return sensitive.isValid(str)
    }

    fun isEqual(strA: String, strB: String): Boolean {
        return StringUtils.equals(strA, strB)
    }

    fun range(str: String, start: Char, end: Char): String {
        return CharMatcher.inRange(start, end).retainFrom(str)
    }

    fun isLinkAvailable(link: String): Boolean {
        val pattern = Pattern.compile("^(http://|https://)?((?:[A-Za-z0-9]+-[A-Za-z0-9]+|[A-Za-z0-9]+)\\.)+([A-Za-z]+)[/\\?\\:]?.*$", Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(link)
        return if (matcher.matches()) {
            true
        } else false
    }

    fun removeAllWhiteSpace(value: String): String {
        return value.replace("\\s+".toRegex(), "")
    }
}
