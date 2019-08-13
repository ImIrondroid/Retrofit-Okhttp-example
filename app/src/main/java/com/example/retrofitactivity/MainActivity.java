package com.example.retrofitactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private TextView textView;

    private final String BASE_URL = "https://api.github.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        executeRetrofit();
        //executeOkHttp();
    }

    void executeRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetApi getApi = retrofit.create(GetApi.class);
        Call<List<User>> call = getApi.sendParam("square","retrofit");

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                List<User> users = response.body();

                for(User user : users) {
                    Log.d("Printed login", user.login);
                    textView.append("login ID : " +user.login+"\n");
                }

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }

    void executeOkHttp() {

        new OkHttpAsyncTask(textView).execute("https://api.github.com/repos/square/retrofit/contributors");

    }
}