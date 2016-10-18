package com.yalin.gsonstudy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * 作者：YaLin
 * 日期：2016/10/17.
 */

public class SerializedNameAnnotationActivity extends AppCompatActivity {
    private static final String TAG = "SerializedName";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_button);
    }

    public void start(View view) {
        Gson gson = new Gson();

        // serialization
        UserSimple userSimple = new UserSimple("Yalin", "nilaynij@gmail.com", true, 26);
        String userStr = gson.toJson(userSimple);
        Log.d(TAG, "userStr = " + userStr);

        // deserialization
        String userJson = "{'age':26,'email':'nilaynij@gmail.com','isDeveloper':true,'fullName':'YaLin'}";
        UserSimple user = gson.fromJson(userJson, UserSimple.class);
        Log.d(TAG, "user = " + user);
    }

    private static class UserSimple {
        @SerializedName("fullName")
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

        @Override
        public String toString() {
            return "UserSimple{" +
                    "name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    ", isDeveloper=" + isDeveloper +
                    ", age=" + age +
                    '}';
        }
    }
}
