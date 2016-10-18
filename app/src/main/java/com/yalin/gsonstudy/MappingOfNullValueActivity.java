package com.yalin.gsonstudy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

/**
 * 作者：YaLin
 * 日期：2016/10/17.
 */

public class MappingOfNullValueActivity extends AppCompatActivity {
    private static final String TAG = "MappingOfNullValue";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_button);
    }

    public void start(View view) {
        Gson gson = new Gson();

        // null serialization
        UserSimple userSimple = new UserSimple(null, "nilaynij@gmail.com", 26, true);
        String userJson = gson.toJson(userSimple);
        Log.d(TAG, "userJson = " + userJson);

        // null deserialization
        String userJson1 = "{'age': 26,'email': 'nilaynij@gmail.com','isDeveloper': true}";
        String userJson2 = "{'email': 'nilaynij@gmail.com','name': 'Norman'}";
        UserSimple user1 = gson.fromJson(userJson1, UserSimple.class);
        UserSimple user2 = gson.fromJson(userJson2, UserSimple.class);
        Log.d(TAG, "user1 = " + user1);
        Log.d(TAG, "user2 = " + user2);
    }

    private static class UserSimple {
        String name;
        String email;
        int age;
        boolean isDeveloper;

        UserSimple(String name, String email, int age, boolean isDeveloper) {
            this.name = name;
            this.email = email;
            this.age = age;
            this.isDeveloper = isDeveloper;
        }

        @Override
        public String toString() {
            return "UserSimple{" +
                    "name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    ", age=" + age +
                    ", isDeveloper=" + isDeveloper +
                    '}';
        }
    }
}
