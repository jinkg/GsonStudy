package com.yalin.gsonstudy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 作者：YaLin
 * 日期：2016/10/18.
 */

public class LenientUsageActivity extends AppCompatActivity {
    private static final String TAG = "LenientUsageActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_button);
    }

    public void start(View view) {
        String userJson = "{email:'nilaynij@gmail.com';'name':'Yalin'}";
        // default action
        Gson gson = new Gson();
        User user = gson.fromJson(userJson, User.class);
        Log.d(TAG, "user = " + user);

        // use lenient feature
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setLenient();
        user = gsonBuilder.create().fromJson(userJson, User.class);
        Log.d(TAG, "user = " + user);
    }

    private static class User {
        String email;
        String name;

        @Override
        public String toString() {
            return "User{" +
                    "email='" + email + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}

