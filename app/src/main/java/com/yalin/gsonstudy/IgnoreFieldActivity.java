package com.yalin.gsonstudy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

/**
 * 作者：YaLin
 * 日期：2016/10/17.
 */

public class IgnoreFieldActivity extends AppCompatActivity {
    private static final String TAG = "IgnoreFieldActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_button);
    }

    public void start(View view) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        // serialization
        UserSimple userSimple = new UserSimple("Yalin", "nilaynij@gmail.com", 26, true, "traditional field", "transient filed");
        String userStr = gson.toJson(userSimple);
        Log.d(TAG, "userJson = " + userStr);

        // deserialization
        String userJson = "{'age':26,'email':'nilaynij@gmail.com','isDeveloper':true,'name':'YaLin','traditionalField':'traditional filed','transientFiled':'transient filed'}";
        UserSimple userSimple1 = gson.fromJson(userJson, UserSimple.class);
        Log.d(TAG, "userSimple1 = " + userSimple1);
    }

    private static class UserSimple {
        @Expose()
        String name;

        @Expose(serialize = false, deserialize = false)
        String email;

        @Expose(serialize = false)
        int age;

        @Expose(deserialize = false)
        boolean isDeveloper;

        String traditionalField;

        transient String transientFiled;

        UserSimple(String name, String email, int age, boolean isDeveloper, String traditionalField, String transientFiled) {
            this.name = name;
            this.email = email;
            this.age = age;
            this.isDeveloper = isDeveloper;
            this.traditionalField = traditionalField;
            this.transientFiled = transientFiled;
        }

        @Override
        public String toString() {
            return "UserSimple{" +
                    "name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    ", age=" + age +
                    ", isDeveloper=" + isDeveloper +
                    ", traditionalField='" + traditionalField + '\'' +
                    ", transientFiled='" + transientFiled + '\'' +
                    '}';
        }
    }
}
