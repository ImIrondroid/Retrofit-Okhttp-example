package com.example.retrofitactivity;

import android.nfc.Tag;
import android.util.Log;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit {

    private static final String TAG = "Retrofit";
    private retrofit2.Retrofit retrofit;

    public void executeRetrofit(final TextView textView) {
        retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(GetApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetApi getApi = retrofit.create(GetApi.class);

        Call<User> call = getApi.getDataUser("10");
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    if (user != null) {
                        logBody("Executed getDataUseR()", user);
                    }
                }
            }


            @Override
            public void onFailure(Call<User> call, Throwable t) {
            }
        });

        Call<List<User>> listCall = getApi.getDataListUser("10");
        listCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> users = response.body();

                for(User user : users) {
                    textView.append("Title : " +user.title +"\n");
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
            }
        });

        Call<User> call1 = getApi.putData("10", new User("200","10","title","body"));
        call1.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()) {
                    User user = response.body();
                    if (user != null) {
                        logBody("Executed putData()",user);
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
            }
        });


        Call<User> call2 = getApi.patchData("1", new User("1","23","title","body"));
        call2.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()) {
                    User user = response.body();
                    if(user!=null) {
                        logBody("Executed patchData()", user);
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });


        HashMap<String, Object> input = new HashMap<>();
        input.put("userId", 1);
        input.put("title", "Title created");
        input.put("body", "Body created");
        Call<User> call3 = getApi.postData(input);
        call3.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()) {
                    User user = response.body();
                    if(user!=null) {
                        logBody("Executed postData()", user);
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });


        Call<ResponseBody> call4 = getApi.deleteData();
        call4.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    ResponseBody body = response.body();
                    if( body != null) {
                        logResponseBody("Executed deleteData()", body);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void logBody(String string, User user) {
        Log.e(TAG , string);
        Log.e(TAG,user.getId());
        Log.e(TAG,user.getUserId());
        Log.e(TAG,user.getTitle());
        Log.e(TAG,user.getBody());
        Log.e(TAG,"====================");
    }

    public void logResponseBody(String string, ResponseBody body) {
        Log.e(TAG, string);
        Log.e(TAG, body.toString());
        Log.e(TAG,"====================");
    }
}