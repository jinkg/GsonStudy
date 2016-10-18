package com.yalin.gsonstudy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;
import java.util.Date;

/**
 * 作者：YaLin
 * 日期：2016/10/18.
 */

public class ExclusionStrategiesActivity extends AppCompatActivity {
    private static final String TAG = "ExclusionStrategies";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_button);
    }

    public void start(View view) {
        UserDate userDate = new UserDate("Yalin", "nilaynij@gmail.com", true, 26);

        // use skip class serialization
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return false;
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return clazz == Date.class || clazz == boolean.class;
            }
        });
        String userJson = gsonBuilder.create().toJson(userDate);
        Log.d(TAG, "userJson = " + userJson);

        // use two function to serialization
        gsonBuilder = new GsonBuilder();
        gsonBuilder.setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return f.getName().contains("_");
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return clazz == Date.class || clazz == boolean.class;
            }
        });
        userJson = gsonBuilder.create().toJson(userDate);
        Log.d(TAG, "userJson = " + userJson);

        // more function
//        gsonBuilder.addSerializationExclusionStrategy();
//        gsonBuilder.addDeserializationExclusionStrategy();

        // exclude field base on modifiers
        UserModifier userModifier = new UserModifier(26, "nilaynij@gmail.com", "Yalin");
        gsonBuilder = new GsonBuilder();
        gsonBuilder.excludeFieldsWithModifiers(Modifier.STATIC, Modifier.FINAL);
        String modifierJson = gsonBuilder.create().toJson(userModifier);
        Log.d(TAG, "modifierJson = " + modifierJson);

        // more function @see IgnoreFieldActivity
//        gsonBuilder.excludeFieldsWithoutExposeAnnotation()

    }

    private static class UserDate {
        private String _name;
        private String email;
        private boolean isDeveloper;
        private int age;
        private Date registerDate = new Date();

        UserDate(String _name, String email, boolean isDeveloper, int age) {
            this._name = _name;
            this.email = email;
            this.isDeveloper = isDeveloper;
            this.age = age;
        }
    }

    private static class UserModifier {
        private String name;
        private transient String email;
        private static boolean isDeveloper;
        private final int age;

        UserModifier(int age, String email, String name) {
            this.age = age;
            this.email = email;
            this.name = name;
        }
    }
}
