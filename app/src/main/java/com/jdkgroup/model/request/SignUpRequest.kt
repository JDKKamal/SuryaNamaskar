package com.jdkgroup.model.request

class SignUpRequest {

    var userid: String? = null
    var username: String? = null
    var email: String? = null
        get() = field!!.toLowerCase()
    @Volatile
    var password: String? = null
    var gender: Int = 0
    var mobile: String? = null
    var country: String? = null
    var state: String? = null
    var pincode: String? = null
    var address: String? = null
    var profilepicture: String? = null
    var logintype: Int = 0
    var status: Int = 0

    constructor() {}

    constructor(username: String, email: String, password: String, gender: Int?, mobile: String, country: String?, state: String?, pincode: String?, address: String?, profilepicture: String?, logintype: Int, status: Int?) {
        this.username = username
        this.email = email
        this.password = password
        this.gender = -1
        this.mobile = mobile
        this.country = ""
        this.state = ""
        this.pincode = ""
        this.address = ""
        this.profilepicture = ""
        this.logintype = logintype
        this.status = 1
    }

    constructor(userid: String, username: String, email: String, password: String, gender: Int, mobile: String, country: String, state: String, pincode: String, address: String) {
        this.userid = userid
        this.username = username
        this.email = email
        this.password = password
        this.gender = gender
        this.mobile = mobile
        this.country = country
        this.state = state
        this.pincode = pincode
        this.address = address
    }

    constructor(email: String, password: String) {
        this.email = email.toLowerCase()
        this.password = password
    }
}

