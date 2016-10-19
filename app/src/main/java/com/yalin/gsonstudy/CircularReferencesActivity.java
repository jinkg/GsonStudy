package com.yalin.gsonstudy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 作者：YaLin
 * 日期：2016/10/19.
 */

public class CircularReferencesActivity extends AppCompatActivity {
    private static final String TAG = "CircularReferences";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_button);
    }

    public void start(View view) {
        UserCircular userCircular = new UserCircular("Yalin", "nilaynij@gmail.com", 26, true);
        userCircular.child = new UserCircular("child", "child@gmail.com", 22, false);
        userCircular.child.parent = userCircular;

        Gson gson = new Gson();
        try {
            gson.toJson(userCircular);
        } catch (StackOverflowError error) {
            error.printStackTrace();
        }

        // exclude parent field
        // or you can use other exclusion method
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.addSerializationExclusionStrategy(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return f.getName().equals("parent");
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        });
        String userJson = gsonBuilder.create().toJson(userCircular);
        Log.d(TAG, "userJson = " + userJson);
    }

    private static class UserCircular {
        String name;
        String email;
        int age;
        boolean isDeveloper;

        UserCircular child;
        UserCircular parent;

        UserCircular(String name, String email, int age, boolean isDeveloper) {
            this.name = name;
            this.email = email;
            this.age = age;
            this.isDeveloper = isDeveloper;
        }
    }
}
