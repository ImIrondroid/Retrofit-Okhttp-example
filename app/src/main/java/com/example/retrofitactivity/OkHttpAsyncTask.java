package com.example.retrofitactivity;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpAsyncTask extends AsyncTask<String, Void, String> {

    OkHttpClient client = new OkHttpClient();
    TextView textView;

    public OkHttpAsyncTask(TextView textView) {
        this.textView = textView;
    }
    @Override
    protected String doInBackground(String... strings) {
        String result = "";
        String strUrl = strings[0];

        try {
            Request request = new Request.Builder()
                    .url(strUrl)
                    .build();
            Response response = client.newCall(request).execute();

            JSONArray jsonArray = new JSONArray(response.body().string());

            for(int i = 0; i< jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String loginid = jsonObject.getString("login");
                result+="login ID : "+loginid+"\n";
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if(s != null) {
            textView.setText(s);
        }
    }
}
