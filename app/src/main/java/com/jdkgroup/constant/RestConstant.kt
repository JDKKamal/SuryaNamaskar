package com.jdkgroup.constant

interface RestConstant {
    companion object {

        //BROWSER URL https://www.coindesk.com/api/

        val BASE_URL = "http://192.168.43.174:17553/interviewquestion/rest/api/"
        val IMAGE_URL = ""

        val API_GET_COUNTRY_LIST = "countrylist"
        val API_POST_SIGN_UP = "signup"
        val API_POST_LOGIN = "login"
        val API_POST_PROFILE = "profile"
        val API_MULTIPART_UPLOAD_USER_PROFILE_PICTURE = "upload_profile"

        val CALL_API_LOGIN = 1;

        val REQUEST_AUTH = 1
        val REQUEST_NO_AUTH = 0

        /* DB STATUS */
        val TYPE_DELETION = 0
        val TYPE_INSERTION = 1
        val TYPE_MODIFICATION = 2

        /* ERROR CODE */
        var ok_200 = 200
        var created_201 = 201
        var accepted_202 = 202
        var non_authoritative_information_203 = 203
        var no_content_203 = 204
        var reset_content_205 = 205

        var found_302 = 302
        var see_other_303 = 303
        var not_modified_304 = 304
        var use_proxy_305 = 305
        var temporary_redirect_307 = 307
        var unused_306 = 306
        var permanent_redirect_308 = 308

        var bad_request_400 = 400 //Bad request
        var unauthorized_401 = 401
        var forbidden_403 = 403 //Account is disabled / Authentication failed / Read disabled / Insufficient account permissions
        var not_found_404 = 404
        var method_not_allowed_405 = 405
        var not_acceptable_406 = 406
        var proxy_authentication_required_407 = 407
        var request_timeout_408 = 408
        var conflict_409 = 409 //Account already exists
        var gone_410 = 410
        var length_required_411 = 411
        var precondition_failed_412 = 412
        var request_entity_too_large_413 = 413
        var unsupported_media_type_415 = 415
        var requested_range_not_satisfiable_416 = 416
        var missing_arguments_419 = 419
        var invalid_arguments_420 = 420
        var too_many_requests_429 = 429

        var internal_server_error_500 = 500
        var not_implemented_501 = 501
        var bad_gateway_502 = 502
        var service_unavailable_503 = 503
        var gateway_timeout_504 = 504
        var http_version_not_supported_505 = 505
        var network_authentication_required_511 = 511
    }
}
