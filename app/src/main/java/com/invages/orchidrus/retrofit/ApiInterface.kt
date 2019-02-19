package com.invages.orchidrus.retrofit

import com.invages.orchidrus.model.LoginBody
import com.invages.orchidrus.retrofit.model.*
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @get:GET("api/unknown")
    val listResources: Call<MultipleResource>

    @POST("/api/users")
    fun createUser(@Body user: User): Call<User>

    @GET("/api/users?")
    fun getUserList(@Query("page") page: String): Call<UserList>

    @POST("/api/users?")
    fun createUserWithField(@Field("name") name: String, @Field("job") job: String): Call<UserList>


    /*
     * App Services
     */


    @POST("orchid/orchid_v0.0.1/public/api/login")
    fun login(@Body body: LoginBody): Call<LoginDetail>

    @FormUrlEncoded
    @POST("orchid/orchid_v0.0.1/public/api/sendOTP")
    fun sendOTP(@Field(email_key) email: String): Call<LoginDetail>

    @FormUrlEncoded
    @POST("orchid/orchid_v0.0.1/public/api/verifyOTP")
    fun verifyOTP(@Field(otp_key) otp: Int, @Field(email_key) email: String): Call<LoginDetail>

    @FormUrlEncoded
    @POST("orchid/orchid_v0.0.1/public/api/listEventType")
    fun getListEventType(@Field(token_key) token: String): Call<ListEvents>

    @FormUrlEncoded
    @POST("orchid/orchid_v0.0.1/public/api/createEvents")
    fun createEvent(
        @Field(event_name) eventName: String,
        @Field(event_description) eventDesc: String,
        @Field(event_type_id) evenTypeId: String,
        @Field(event_start_time) eventStartTime: String,
        @Field(event_response_by_time) eventResponseTime: String,
        @Field(event_created_by) eventCreatedBy: String
    ): Call<LoginDetail>


    companion object {

        const val otp_key = "otp"
        const val email_key = "email"
        const val password_key = "password"
        const val token_key = "token"


        const val event_name = "event_name"
        const val event_description = "event_description"
        const val event_type_id = "event_type_id"
        const val event_start_time = "event_start_time"
        const val event_response_by_time = "event_response_by_time"
        const val event_created_by = "event_created_by"
    }

}
