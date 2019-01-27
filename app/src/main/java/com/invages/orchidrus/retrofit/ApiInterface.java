package com.invages.orchidrus.retrofit;

import com.invages.orchidrus.retrofit.model.MultipleResource;
import com.invages.orchidrus.retrofit.model.User;
import com.invages.orchidrus.retrofit.model.UserList;
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

}
