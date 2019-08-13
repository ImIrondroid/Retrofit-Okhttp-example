package com.example.retrofitactivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetApi {

    @GET("/repos/{owner}/{repository}/contributors")

    // GetApi의 param을 넘기는 메소드
    Call<List<User>> sendParam(
            @Path("owner") String owner,
            @Path("repository") String repository
    );
}
