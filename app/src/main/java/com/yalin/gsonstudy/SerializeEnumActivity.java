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
 * 日期：2016/10/19.
 */

public class SerializeEnumActivity extends AppCompatActivity {
    private static final String TAG = "SerializeEnumActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_button);
    }

    public void start(View view) {
        UserDayEnum userDayEnum = new UserDayEnum("Yalin", "nilaynij@gmail.com", true, 26, Day.TUESDAY);

        Gson gson = new Gson();
        // serialize enum
        String userDayJson = gson.toJson(userDayEnum);
        Log.d(TAG, "userDayJson = " + userDayJson);

        // deserialize enum
        String userJson = "{'email': 'norman@futurestud.io','age': 26,'day': 'WEDNESDAY'}";
        String userJson2 = "{'email': 'norman@futurestud.io','age': 26,'day':4}";
        UserDayEnum userEnum = gson.fromJson(userJson, UserDayEnum.class);
        UserDayEnum userEnum2 = gson.fromJson(userJson2, UserDayEnum.class);
        Log.d(TAG, "userEnum = " + userEnum.toString());
        Log.d(TAG, "userEnum2 = " + userEnum2.toString());
    }

    private enum Day {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        @SerializedName("LazyDay1")
        SATURDAY,
        @SerializedName("LazyDay2")
        SUNDAY
    }

    private static class UserDayEnum {
        private String name;
        private String email;
        private boolean isDeveloper;
        private int age;

        private Day day = Day.FRIDAY;

        UserDayEnum(String name, String email, boolean isDeveloper, int age, Day day) {
            this.name = name;
            this.email = email;
            this.isDeveloper = isDeveloper;
            this.age = age;
            this.day = day;
        }

        @Override
        public String toString() {
            return "UserDayEnum{" +
                    "name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    ", isDeveloper=" + isDeveloper +
                    ", age=" + age +
                    ", day=" + day +
                    '}';
        }
    }
}
