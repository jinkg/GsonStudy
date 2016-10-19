package com.yalin.gsonstudy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 作者：YaLin
 * 日期：2016/10/19.
 */

public class CustomSerializationPart1Activity extends AppCompatActivity {
    private static final String TAG = "CustomSerialization";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_button);
    }

    public void start(View view) {
        Merchant futureStudio = new Merchant(23, "Future Studio");
        Merchant coffeeStudio = new Merchant(46, "Coffee Studio");

        List<Merchant> subscribedMerchant = Arrays.asList(futureStudio, coffeeStudio);

        UserSubscription subscription = new UserSubscription("Yalin",
                "nilaynij@gmail.com", 26, true, subscribedMerchant);

        // full json
        Gson gson = new Gson();
        String generalJson = gson.toJson(subscription);
        Log.d(TAG, "generalJson =" + generalJson);

        // custom serialization with single Merchant object
        GsonBuilder gsonBuilder = new GsonBuilder();
        JsonSerializer<Merchant> merchantSerializer = new JsonSerializer<Merchant>() {
            @Override
            public JsonElement serialize(Merchant src, Type typeOfSrc, JsonSerializationContext context) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("id", src.id);

                return jsonObject;
            }
        };
        gsonBuilder.registerTypeAdapter(Merchant.class, merchantSerializer);
        String customMerchantJson = gsonBuilder.create().toJson(subscription);
        Log.d(TAG, "customMerchantJson = " + customMerchantJson);

        // custom serialization with Merchant list
        Type listMerchantType = new TypeToken<List<Merchant>>() {
        }.getType();
        gsonBuilder = new GsonBuilder();
        JsonSerializer<List<Merchant>> listMerchantSerializer = new JsonSerializer<List<Merchant>>() {
            @Override
            public JsonElement serialize(List<Merchant> src, Type typeOfSrc, JsonSerializationContext context) {
                JsonObject jsonObject = new JsonObject();

                List<String> merchantIds = new ArrayList<>(src.size());
                for (Merchant merchant : src) {
                    merchantIds.add(merchant.id + "");
                }

                String merchantIdsAsString = TextUtils.join(",", merchantIds);
                jsonObject.addProperty("ids", merchantIdsAsString);

                return jsonObject;
            }
        };
        gsonBuilder.registerTypeAdapter(listMerchantType, listMerchantSerializer);
        String listMerchantJson = gsonBuilder.create().toJson(subscription);
        Log.d(TAG, "listMerchantJson = " + listMerchantJson);

        // custom serialization with Merchant list
        gsonBuilder = new GsonBuilder();
        JsonSerializer<List<Merchant>> listMerchantAsArraySerializer = new JsonSerializer<List<Merchant>>() {
            @Override
            public JsonElement serialize(List<Merchant> src, Type typeOfSrc, JsonSerializationContext context) {
                JsonArray jsonArray = new JsonArray();
                for (Merchant merchant : src) {
                    jsonArray.add(merchant.id);
                }
                return jsonArray;
            }
        };

        gsonBuilder.registerTypeAdapter(listMerchantType, listMerchantAsArraySerializer);
        String listMerchantAsArrayJson = gsonBuilder.create().toJson(subscription);
        Log.d(TAG, "listMerchantAsArrayJson = " + listMerchantAsArrayJson);
    }

    private static class UserSubscription {
        String name;
        String email;
        int age;
        boolean isDeveloper;

        List<Merchant> merchantList;

        UserSubscription(String name, String email, int age, boolean isDeveloper, List<Merchant> merchantList) {
            this.name = name;
            this.email = email;
            this.age = age;
            this.isDeveloper = isDeveloper;
            this.merchantList = merchantList;
        }
    }

    private static class Merchant {
        int id;
        String name;

        Merchant(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}
