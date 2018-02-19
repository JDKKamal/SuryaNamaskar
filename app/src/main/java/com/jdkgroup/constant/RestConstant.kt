package com.jdkgroup.constant

interface RestConstant {
    companion object {

        //BROWSER URL https://www.coindesk.com/api/

        val BASE_URL = "http://192.168.43.174:17553/interviewquestion/rest/api/"
        val IMAGE_URL = ""

        val API_GET_COUNTRY_LIST = "countrylist"
        val API_MULTIPART_UPLOAD_USER_PROFILE_PICTURE = "upload_profile"

        val REQUEST_AUTH = 1
        val REQUEST_NO_AUTH = 0

        /* DB STATUS */
        val TYPE_DELETION = 0
        val TYPE_INSERTION = 1
        val TYPE_MODIFICATION = 2

        /* ERROR CODE */
        val ERROR_SERVICE_UNAVAILABLE = 503
        val ERROR_INTERNAL_SERVER = 500
        val ERROR_BAD_Gateway = 502
        val ERROR_NOT_FOUND = 404
        val ERROR_Forbidden = 403
        val ERROR_PRECONDITION_FAILED = 403
        val ERROR_OK = 200
        val ERROR_No_Content = 204
        val ERROR_Method_Not_Allowed = 405
        val ERROR_RESPONSE_ERROR = 400
    }
}
