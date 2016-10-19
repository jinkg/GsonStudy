package com.yalin.gsonstudy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：YaLin
 * 日期：2016/10/19.
 */

public class GenericsActivity extends AppCompatActivity {
    private static final String TAG = "GenericsActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_button);
    }

    public void start(View view) {
        Gson gson = new Gson();

        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);

        List<String> stringList = new ArrayList<>();
        stringList.add("1");
        stringList.add("2");
        stringList.add("3");

        // general serialization
        String integerJson = gson.toJson(integerList);
        String stringJson = gson.toJson(stringList);
        Log.d(TAG, "integerJson = " + integerJson);
        Log.d(TAG, "stringJson = " + stringJson);

        // TypeToken serialization
        Type integerType = new TypeToken<List<Integer>>() {
        }.getType();
        Type stringType = new TypeToken<List<String>>() {
        }.getType();
        String integerTypeJson = gson.toJson(integerList, integerType);
        String stringTypeJson = gson.toJson(stringList, stringType);
        Log.d(TAG, "integerTypeJson = " + integerTypeJson);
        Log.d(TAG, "stringTypeJson = " + stringTypeJson);

        Box<String> stringBox = new Box<>("String Type");
        Box<Integer> integerBox = new Box<>(42);

        Box<UserData> userDataBox = new Box<>(new UserData("Yalin", "nilaynij@gmail.com", 26, true));

        // general serialization
        String generalStringBoxJson = gson.toJson(stringBox);
        String generalIntegerBoxJson = gson.toJson(integerBox);
        String generalUserDataBoxJson = gson.toJson(userDataBox);
        Log.d(TAG, "generalStringBoxJson = " + generalStringBoxJson);
        Log.d(TAG, "generalIntegerBoxJson = " + generalIntegerBoxJson);
        Log.d(TAG, "generalUserDataBoxJson = " + generalUserDataBoxJson);

        // TypeToken serialization
        Type stringBoxType = new TypeToken<Box<String>>() {
        }.getType();
        Type integerBoxType = new TypeToken<Box<Integer>>() {
        }.getType();
        Type userDataBoxType = new TypeToken<Box<UserData>>() {
        }.getType();
        String typeTokenStringBoxJson = gson.toJson(stringBox, stringBoxType);
        String typeTokenIntegerBoxJson = gson.toJson(integerBox, integerBoxType);
        String typeTokenUserDataBoxJson = gson.toJson(userDataBox, userDataBoxType);
        Log.d(TAG, "typeTokenStringBoxJson = " + typeTokenStringBoxJson);
        Log.d(TAG, "typeTokenIntegerBoxJson = " + typeTokenIntegerBoxJson);
        Log.d(TAG, "typeTokenUserDataBoxJson = " + typeTokenUserDataBoxJson);

        String userDataJson = "{'boxContent': {'name': 'Yalin','age': 26,'email': 'nilaynij@fgmail.com','isDeveloper': true, 'registerDate': 'Jun 7, 2016 7:15:29 AM'}}";

        // with out use TypeToken
        Box<UserData> boxWithoutData = gson.fromJson(userDataJson, Box.class);
        Log.d(TAG, "boxWithoutData = " + boxWithoutData);

        // use TypeToken deserialization
        Type complexType = new TypeToken<Box<UserData>>() {
        }.getType();
        Box boxWithData = gson.fromJson(userDataJson, complexType);
        Log.d(TAG, "boxWithData = " + boxWithData);
    }

    private static class Box<T> {
        private T boxContent;

        Box(T boxContent) {
            this.boxContent = boxContent;
        }

        @Override
        public String toString() {
            return "Box{" +
                    "boxContent=" + boxContent +
                    '}';
        }
    }

    private static class UserData {
        String name;
        String email;
        int age;
        boolean isDeveloper;

        UserData(String name, String email, int age, boolean isDeveloper) {
            this.name = name;
            this.email = email;
            this.age = age;
            this.isDeveloper = isDeveloper;
        }

        @Override
        public String toString() {
            return "UserData{" +
                    "name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    ", age=" + age +
                    ", isDeveloper=" + isDeveloper +
                    '}';
        }
    }
}
