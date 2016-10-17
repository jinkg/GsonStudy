package com.yalin.gsonstudy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.yalin.gsonstudy.model.NestedUserSimple;
import com.yalin.gsonstudy.model.Restaurant;
import com.yalin.gsonstudy.model.UserAddress;

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

        Gson gson = new Gson();
        String jsonStr = gson.toJson(userSimple);
        Log.d(TAG, "jsonStr = " + jsonStr);

        String json = "{'name': 'Future Studio Steak House','owner': { 'name': 'Christian','address': { 'city': 'Magdeburg', 'country': 'Germany',\n" +
                "      'houseNumber': '42A', 'street': 'Main Street'   } },'cook': {  'age': 18,  'name': 'Marcus',   'salary': 1500\n" +
                "  },'waiter': {'age': 18,'name': 'Norman', 'salary': 1000 }}";

        Restaurant restaurant = gson.fromJson(json, Restaurant.class);
        Log.d(TAG, "restaurant = " + restaurant);

    }
}
