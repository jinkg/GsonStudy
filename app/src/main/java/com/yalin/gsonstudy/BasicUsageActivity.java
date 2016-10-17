package com.yalin.gsonstudy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.yalin.gsonstudy.model.UserSimple;

/**
 * 作者：YaLin
 * 日期：2016/10/17.
 */

public class BasicUsageActivity extends AppCompatActivity {
    private static final String TAG = "BasicUsageActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_button);
    }

    public void start(View view) {
        UserSimple userSimple = new UserSimple();
        userSimple.name = "YaLin";
        userSimple.email = "nilaynij@gmail.com";
        userSimple.age = 26;
        userSimple.isDeveloper = true;

        Gson gson = new Gson();
        String jsonStr = gson.toJson(userSimple);
        Log.d(TAG, "jsonStr = " + jsonStr);

        String userJson = "{'age':26,'email':'nilaynij@futurestud.io','isDeveloper':true,'name':'YaLin'}";
        UserSimple userSimple1 = gson.fromJson(userJson, UserSimple.class);
        Log.d(TAG, "userSimple1 = " + userSimple1.toString());
    }
}
