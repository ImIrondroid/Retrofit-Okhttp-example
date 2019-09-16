package com.example.retrofitactivity;

import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetApi {

    public static final String BASE_URL = "http://jsonplaceholder.typicode.com";

    @GET("posts/{id}")
    Call<User> getDataUser(@Path("id") String id);

    @GET("/posts")
    Call<List<User>> getDataListUser(@Query("userId") String userId);

    @FormUrlEncoded
    @POST("/posts")
    Call<User> postData(@FieldMap HashMap<String, Object> param);

    //요청된 자원을 수정한다, PUT의 경우 내용 전체를 갱신하는 것을 의미한다.
    @PUT("/posts/{id}")
    Call<User> putData(@Path("id") String id, @Body User user);


    //요청된 자원을 수정할때 사용한다, PATCH는 해당 자원의 일부를 교채한다.
    @PATCH("/posts/{id}")
    Call<User> patchData(@Path("id") String id, @Body User user);

    // Call<ResponseBody> 에서 ResponseBody는 통신을 통해 되돌려 받는 값이 없을때 사용함.
    @DELETE("/posts/1")
    Call<ResponseBody> deleteData();
}
