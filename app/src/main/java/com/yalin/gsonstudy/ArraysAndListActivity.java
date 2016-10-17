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
import java.util.Arrays;
import java.util.List;

/**
 * 作者：YaLin
 * 日期：2016/10/17.
 */

public class ArraysAndListActivity extends AppCompatActivity {
    private static final String TAG = "ArraysAndListActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_button);
    }

    public void start(View view) {
        List<RestaurantMenuItem> menu = new ArrayList<>();
        menu.add(new RestaurantMenuItem("Spaghetti", 7.99f));
        menu.add(new RestaurantMenuItem("Steak", 12.99f));
        menu.add(new RestaurantMenuItem("Salad", 5.99f));

        RestaurantWithMenu restaurant = new RestaurantWithMenu("Future Studio Steak House", menu);

        Gson gson = new Gson();

        // nested list serialization
        String restaurantJson = gson.toJson(restaurant);
        Log.d(TAG, "restaurantJson = " + restaurantJson);

        // list serialization
        String menuJson = gson.toJson(menu);
        Log.d(TAG, "menuJson = " + menuJson);

        // array deserialization
        String founderJson = "[{'name': 'Christian','flowerCount': 1}, {'name': 'Marcus', 'flowerCount': 3}, {'name': 'Norman', 'flowerCount': 2}]";
        Founder[] founderArray = gson.fromJson(founderJson, Founder[].class);
        Log.d(TAG, "founderArray = " + Arrays.toString(founderArray));

        // list deserialization
        Type founderListType = new TypeToken<ArrayList<Founder>>() {
        }.getType();
        ArrayList<Founder> founderList = gson.fromJson(founderJson, founderListType);
        Log.d(TAG, "founderList = " + founderList);
    }

    static class RestaurantWithMenu {
        String name;

        List<RestaurantMenuItem> menu;

        RestaurantWithMenu(String name, List<RestaurantMenuItem> menu) {
            this.name = name;
            this.menu = menu;
        }

        @Override
        public String toString() {
            return "RestaurantWithMenu{" +
                    "name='" + name + '\'' +
                    ", menu=" + menu +
                    '}';
        }
    }

    static class RestaurantMenuItem {
        String description;
        float price;

        RestaurantMenuItem(String description, float price) {
            this.description = description;
            this.price = price;
        }

        @Override
        public String toString() {
            return "RestaurantMenuItem{" +
                    "description='" + description + '\'' +
                    ", price=" + price +
                    '}';
        }
    }

    static class Founder {
        String name;
        int flowerCount;

        @Override
        public String toString() {
            return "Founder{" +
                    "flowerCount=" + flowerCount +
                    ", name='" + name + '\'' +
                    '}';
        }
    }


}
