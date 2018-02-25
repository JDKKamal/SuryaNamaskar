package com.jdkgroup.model.request

class LoginRequest {
    var email: String? = null
    var password: String? = null

    constructor() {}

    constructor(email: String, password: String) {
        this.email = email
        this.password = password
    }
}
