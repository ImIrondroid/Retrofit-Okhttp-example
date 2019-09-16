package com.example.retrofitactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        //executeOkHttp();
        executeRetrofit();

    }

    public void executeOkHttp() {
        new OkHttpAsyncTask(textView).execute("https://api.github.com/repos/square/retrofit/contributors");
    }

    public void executeRetrofit() {
        new Retrofit().executeRetrofit(textView);
    }
}