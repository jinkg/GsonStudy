package com.yalin.gsonstudy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Field;

/**
 * 作者：YaLin
 * 日期：2016/10/17.
 */

public class GsonBuilderNamingPoliciesActivity extends AppCompatActivity {
    private static final String TAG = "NamingPolicies";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_button);
    }

    public void start(View view) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY);

        // serialization with FieldNamingPolicy.IDENTITY policy
        UserNaming user = new UserNaming("Yalin", "nilaynij@gmail.com", true, 26);
        user.serializedNameFiled = "serializedNameFiled";
        String userJson = gsonBuilder.create().toJson(user);
        Log.d(TAG, "userJson = " + userJson);

        // serialization with FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES policy
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        userJson = gsonBuilder.create().toJson(user);
        Log.d(TAG, "userJson = " + userJson);

        // serialization with FieldNamingPolicy.LOWER_CASE_WITH_DASHES policy
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES);
        userJson = gsonBuilder.create().toJson(user);
        Log.d(TAG, "userJson = " + userJson);

        // serialization with FieldNamingPolicy.UPPER_CAMEL_CASE policy
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE);
        userJson = gsonBuilder.create().toJson(user);
        Log.d(TAG, "userJson = " + userJson);

        // serialization with FieldNamingPolicy.UPPER_CAMEL_CASE_WITH_SPACES policy
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE_WITH_SPACES);
        userJson = gsonBuilder.create().toJson(user);
        Log.d(TAG, "userJson = " + userJson);

        // serialization with custom filed naming
        FieldNamingStrategy customPolicy = new FieldNamingStrategy() {

            @Override
            public String translateName(Field f) {
                return f.getName().replace("_", "");
            }
        };
        gsonBuilder.setFieldNamingStrategy(customPolicy);
        userJson = gsonBuilder.create().toJson(user);
        Log.d(TAG, "userJson = " + userJson);

        // deserialization with FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES policy
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        String reviewerJson = "{'reviewer_name': 'Marcus'}";
        PostReviewer postReviewer = gsonBuilder.create().fromJson(reviewerJson, PostReviewer.class);
        Log.d(TAG, "postReviewer = " + postReviewer);
    }

    private static class UserNaming {
        String Name;
        String email_of_developer;
        boolean isDeveloper;
        int _ageOfDeveloper;

        @SerializedName("serializedName")
        String serializedNameFiled;

        UserNaming(String name, String email_of_developer, boolean isDeveloper, int _ageOfDeveloper) {
            Name = name;
            this.email_of_developer = email_of_developer;
            this.isDeveloper = isDeveloper;
            this._ageOfDeveloper = _ageOfDeveloper;
        }
    }

    private static class PostReviewer {
        String reviewerName;

        @Override
        public String toString() {
            return "PostReviewer{" +
                    "reviewerName='" + reviewerName + '\'' +
                    '}';
        }
    }
}
