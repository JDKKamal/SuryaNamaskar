package com.jdkgroup.connection

interface TokenManager {
    val token: String
    fun hasToken(): Boolean
    fun clearToken()
    fun refreshToken()
}
