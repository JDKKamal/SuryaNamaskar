package com.jdkgroup.connection

import com.jdkgroup.model.api.Response
import com.jdkgroup.model.api.countrylist.CountryResponse
import com.jdkgroup.model.api.signup.SignUpResponse
import com.jdkgroup.model.request.LoginRequest
import com.jdkgroup.model.request.SignUpRequest

import java.util.HashMap

import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.PartMap
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface RestService {
    @POST
    fun apiPost(@Url url: String): Observable<String>

    @GET
    fun apiGetCountryList(@Url url: String): Observable<CountryResponse>

    @POST
    fun apiPostSignUp(@Url url: String, @Body signUpRequest : SignUpRequest): Observable<SignUpResponse>

    @POST
    fun apiPostLogin(@Url url: String, @Body loginRequest: LoginRequest): Observable<SignUpResponse>

    @Multipart
    @POST
    fun apiMultipartUploadUserProfilePicture(@Url url: String, @Part file: MultipartBody.Part): Observable<Response>

    @FormUrlEncoded
    @PUT
    fun apiPut(@Url url: String, @FieldMap params: HashMap<String, String>): Observable<String>

    @FormUrlEncoded
    @HTTP(method = "DELETE", hasBody = true)
    fun apiDelete(@Url url: String, @FieldMap params: HashMap<String, String>): Observable<String>

    @DELETE
    fun apiDeleteMember(@Url url: String, @Body params: String): Observable<String>

    @Multipart
    @POST
    fun apiPostWithUpload(@Url url: String, @PartMap params: HashMap<String, RequestBody>, @Part body: MultipartBody.Part): Observable<String>

    @FormUrlEncoded
    @PUT
    fun apiupdateSettingsPut(@Url url: String, @FieldMap params: HashMap<String, Any>): Observable<String>

    @GET
    fun getLocationNameFromLatLng(@Url url: String, @QueryMap options: Map<String, String>): Observable<String>

    @GET
    fun apiGet(@Url url: String): Observable<String>
}


