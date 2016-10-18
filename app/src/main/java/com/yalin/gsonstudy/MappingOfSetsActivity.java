package com.yalin.gsonstudy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashSet;

/**
 * 作者：YaLin
 * 日期：2016/10/17.
 */

public class MappingOfSetsActivity extends AppCompatActivity {
    private static final String TAG = "MappingOfSetsActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_button);
    }

    public void start(View view) {
        Gson gson = new Gson();

        HashSet<String> users = new HashSet<>();
        users.add("Christian");
        users.add("Marcus");
        users.add("Norman");
        users.add("Marcus");

        // sets serialization
        String usersJson = gson.toJson(users);
        Log.d(TAG, "usersJson = " + usersJson);

        // sets deserialization
        String founderJson = "[{'name': 'Christian','flowerCount': 1}, {'name': 'Marcus', 'flowerCount': 3}, {'name': 'Norman', 'flowerCount': 2}]";
        Type founderSetType = new TypeToken<HashSet<Founder>>() {
        }.getType();
        HashSet<Founder> founderSet = gson.fromJson(founderJson, founderSetType);
        Log.d(TAG, "founderSet = " + founderSet);
    }

    private static class Founder {
        String name;
        int flowerCount;

        @Override
        public String toString() {
            return "Founder{" +
                    "name='" + name + '\'' +
                    ", flowerCount=" + flowerCount +
                    '}';
        }
    }
}
