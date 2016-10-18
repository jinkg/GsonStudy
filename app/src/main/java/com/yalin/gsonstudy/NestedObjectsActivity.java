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

public class NestedObjectsActivity extends AppCompatActivity {
    private static final String TAG = "NestedObjectsActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_button);
    }

    public void start(View view) {
        UserAddress userAddress = new UserAddress();
        userAddress.city = "Beijing";
        userAddress.country = "China";
        userAddress.houseNumber = "301";
        userAddress.street = "ZhongGuanCun";

        NestedUserSimple userSimple = new NestedUserSimple();
        userSimple.name = "YaLin";
        userSimple.email = "nilaynij@gmail.com";
        userSimple.age = 26;
        userSimple.isDeveloper = true;
        userSimple.userAddress = userAddress;

        // nested objects serialization
        Gson gson = new Gson();
        String jsonStr = gson.toJson(userSimple);
        Log.d(TAG, "jsonStr = " + jsonStr);

        String json = "{'name': 'Future Studio Steak House','owner': { 'name': 'Christian','address': { 'city': 'Magdeburg', 'country': 'Germany',\n" +
                "      'houseNumber': '42A', 'street': 'Main Street'   } },'cook': {  'age': 18,  'name': 'Marcus',   'salary': 1500\n" +
                "  },'waiter': {'age': 18,'name': 'Norman', 'salary': 1000 }}";

        // nested objects deserialization
        Restaurant restaurant = gson.fromJson(json, Restaurant.class);
        Log.d(TAG, "restaurant = " + restaurant);

    }

    private static class NestedUserSimple {
        String name;
        String email;
        int age;
        boolean isDeveloper;

        UserAddress userAddress;

        @Override
        public String toString() {
            return "NestedUserSimple{" +
                    "name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    ", age=" + age +
                    ", isDeveloper=" + isDeveloper +
                    ", userAddress=" + userAddress +
                    '}';
        }
    }

    private static class UserAddress {
        String street;
        String houseNumber;
        String city;
        String country;

        @Override
        public String toString() {
            return "UserAddress{" +
                    "street='" + street + '\'' +
                    ", houseNumber='" + houseNumber + '\'' +
                    ", city='" + city + '\'' +
                    ", country='" + country + '\'' +
                    '}';
        }
    }

    private static class Restaurant {
        String name;

        Owner owner;
        Staff cook;
        Staff waiter;

        @Override
        public String toString() {
            return "Restaurant{" +
                    "name='" + name + '\'' +
                    ", owner=" + owner +
                    ", cook=" + cook +
                    ", waiter=" + waiter +
                    '}';
        }
    }

    private static class Owner {
        String name;
        UserAddress address;

        @Override
        public String toString() {
            return "Owner{" +
                    "name='" + name + '\'' +
                    ", address=" + address +
                    '}';
        }
    }

    private static class Staff {
        String name;
        int age;
        int salary;

        @Override
        public String toString() {
            return "Staff{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", salary=" + salary +
                    '}';
        }
    }
}
