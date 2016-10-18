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

public class ForceSerializationNullValueActivity extends AppCompatActivity {
    private static final String TAG = "SerializationNullValue";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_button);
    }

    public void start(View view) {
        UserSimple userSimple = new UserSimple(null, "nilaynij@gmail.com", true, 26);

        // normal serialization
        Gson gson = new Gson();
        String normalJson = gson.toJson(userSimple);
        Log.d(TAG, "normalJson = " + normalJson);

        // serialize null value
        gson = new GsonBuilder().serializeNulls().create();
        String serializeNullJson = gson.toJson(userSimple);
        Log.d(TAG, "serializeNullJson = " + serializeNullJson);
    }

    private static class UserSimple {
        String name;
        String email;
        boolean isDeveloper;
        int age;

        UserSimple(String name, String email, boolean isDeveloper, int age) {
            this.name = name;
            this.email = email;
            this.isDeveloper = isDeveloper;
            this.age = age;
        }
    }
}
