package com.yalin.gsonstudy;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;

import java.lang.reflect.Type;

/**
 * 作者：YaLin
 * 日期：2016/10/21.
 */

public class CustomInstanceCreatorActivity extends AppCompatActivity {
    private static final String TAG = "CustomInstanceCreator";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_button);
    }

    public void start(View view) {
        String userJson = "{'age': 26,'email': 'nilaynij@gmail.com','isDeveloper': true,'name': 'Yalin'}";

        // general deserialization
        Gson gson = new Gson();
        UserData generalData = gson.fromJson(userJson, UserData.class);
        Log.d(TAG, "generalData = " + generalData);

        // use custom creator
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(
                UserData.class,
                new UserDataInstanceCreator(getApplicationContext())
        );
        UserData creatorData = gsonBuilder.create().fromJson(userJson, UserData.class);
        Log.d(TAG, "creatorData = " + creatorData);

    }

    private static class UserData {
        String name;
        String email;
        int age;
        boolean isDeveloper;

        Context context;
        int defaultValue;

        UserData(Context context) {
            this.context = context;
            this.defaultValue = 3;
        }

        @Override
        public String toString() {
            return "UserData{" +
                    "name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    ", age=" + age +
                    ", isDeveloper=" + isDeveloper +
                    ", context=" + context +
                    ", defaultValue=" + defaultValue +
                    '}';
        }
    }

    private static class UserDataInstanceCreator implements InstanceCreator<UserData> {

        private Context mContext;

        UserDataInstanceCreator(Context context) {
            this.mContext = context;
        }

        @Override
        public UserData createInstance(Type type) {
            UserData userData = new UserData(mContext);
            userData.name = "default";
            userData.defaultValue = 10;
            return userData;
        }
    }
}
