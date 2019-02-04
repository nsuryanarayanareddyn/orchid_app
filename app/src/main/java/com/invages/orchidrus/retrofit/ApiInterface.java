package com.invages.orchidrus.retrofit;

import com.invages.orchidrus.retrofit.model.*;
import retrofit2.Call;
import retrofit2.http.*;

public interface ApiInterface {

    @GET("api/unknown")
    Call<MultipleResource> getListResources();

    @POST("/api/users")
    Call<User> createUser(@Body User user);

    @GET("/api/users?")
    Call<UserList> getUserList(@Query("page") String page);

    @POST("/api/users?")
    Call<UserList> createUserWithField(@Field("name") String name, @Field("job") String job);

    /*
     * Key constants
     */
    String otp_key = "otp";
    String email_key = "email";
    String password_key = "password";
    String token_key = "token";

    /*
     * App Services
     */
    @FormUrlEncoded
    @POST("orchid/orchid_v0.0.1/public/api/login")
    Call<LoginDetail> login(@Field(email_key) String email, @Field(password_key) String password);

    @FormUrlEncoded
    @POST("orchid/orchid_v0.0.1/public/api/sendOTP")
    Call<LoginDetail> sendOTP(@Field(email_key) String email);

    @FormUrlEncoded
    @POST("orchid/orchid_v0.0.1/public/api/verifyOTP")
    Call<LoginDetail> verifyOTP(@Field(otp_key) int otp, @Field(email_key) String email);

    @FormUrlEncoded
    @POST("orchid/orchid_v0.0.1/public/api/listEventType")
    Call<ListEvents> getlistEventType(@Field(token_key) String token);

}
