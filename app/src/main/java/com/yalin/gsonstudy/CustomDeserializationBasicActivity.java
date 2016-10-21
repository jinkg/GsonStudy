package com.yalin.gsonstudy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * 作者：YaLin
 * 日期：2016/10/21.
 */

public class CustomDeserializationBasicActivity extends AppCompatActivity {
    private static final String TAG = "CustomDeserialization";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_button);
    }

    public void start(View view) {
        String userJson = "{'year': 2016,'month': 9,'day': 21,'age': 26,'email': 'nilaynij@gmail.com','isDeveloper': true,'name': 'Yalin'}";

        // custom deserialization
        JsonDeserializer<UserData> jsonDeserializer = new JsonDeserializer<UserData>() {
            @Override
            public UserData deserialize(JsonElement json, Type typeOfT,
                                        JsonDeserializationContext context) throws JsonParseException {
                JsonObject jsonObject = json.getAsJsonObject();
                @SuppressWarnings("deprecation")
                Date date = new Date(
                        jsonObject.get("year").getAsInt(),
                        jsonObject.get("month").getAsInt(),
                        jsonObject.get("day").getAsInt());
                return new UserData(
                        jsonObject.get("name").getAsString(),
                        jsonObject.get("email").getAsString(),
                        jsonObject.get("age").getAsInt(),
                        jsonObject.get("isDeveloper").getAsBoolean(),
                        date
                );
            }
        };
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(UserData.class, jsonDeserializer);
        UserData userData = gsonBuilder.create().fromJson(userJson, UserData.class);
        Log.d(TAG, "userData = " + userData);
    }

    private static class UserData {
        String name;
        String email;
        int age;
        boolean isDeveloper;
        Date registerDate;

        UserData(String name, String email, int age, boolean isDeveloper, Date registerDate) {
            this.name = name;
            this.email = email;
            this.age = age;
            this.isDeveloper = isDeveloper;
            this.registerDate = registerDate;
        }

        @Override
        public String toString() {
            return "UserData{" +
                    "name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    ", age=" + age +
                    ", isDeveloper=" + isDeveloper +
                    ", registerDate=" + registerDate +
                    '}';
        }
    }
}
