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

public class SpecialValueOfFloatDoubleActivity extends AppCompatActivity {
    private static final String TAG = "SpecialValue";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_button);
    }

    public void start(View view) {
        UserFloat userFloat = new UserFloat("Yalin", Float.POSITIVE_INFINITY);
        Gson gson = new Gson();
        try {
            gson.toJson(userFloat);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        // use serializeSpecialFloatingPointValues()
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeSpecialFloatingPointValues();
        String floatJson = gsonBuilder.create().toJson(userFloat);
        Log.d(TAG, "floatJson = " + floatJson);
    }

    private static class UserFloat {
        String name;
        Float weight;

        UserFloat(String name, Float weight) {
            this.name = name;
            this.weight = weight;
        }
    }
}
