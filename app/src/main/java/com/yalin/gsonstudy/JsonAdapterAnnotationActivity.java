package com.yalin.gsonstudy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.annotations.JsonAdapter;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 作者：YaLin
 * 日期：2016/10/21.
 */

public class JsonAdapterAnnotationActivity extends AppCompatActivity {
    private static final String TAG = "JsonAdapterAnnotation";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_button);
    }

    public void start(View view) {
        Gson gson = new Gson();

        Merchant futureStudio = new Merchant(23, "Future Studio");
        Merchant coffeeStudio = new Merchant(46, "Coffee Studio");

        List<Merchant> subscribedMerchant = Arrays.asList(futureStudio, coffeeStudio);

        UserSubscription subscription = new UserSubscription("Yalin",
                "nilaynij@gmail.com", 26, true, subscribedMerchant);

        String merchantJson = gson.toJson(subscription);
        Log.d(TAG, "merchantJson = " + merchantJson);

        String userJson = "{'year': 2016,'month': 9,'day': 21,'age': 26,'email': 'nilaynij@gmail.com','isDeveloper': true,'name': 'Yalin'}";
        UserData userData = gson.fromJson(userJson, UserData.class);
        Log.d(TAG, "userData = " + userData);

    }

    private static class UserSubscription {
        String name;
        String email;
        int age;
        boolean isDeveloper;

        // serializer
        @JsonAdapter(MerchantListSerializer.class)
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

    private static class MerchantListSerializer implements JsonSerializer<List<Merchant>> {

        @Override
        public JsonElement serialize(List<Merchant> src, Type typeOfSrc, JsonSerializationContext context) {
            JsonArray jsonArray = new JsonArray();
            for (Merchant merchant : src) {
                jsonArray.add(merchant.id);
            }
            return jsonArray;
        }
    }

    // deserializer
    @JsonAdapter(UserDataDeserializer.class)
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

    private static class UserDataDeserializer implements JsonDeserializer<UserData> {

        @Override
        public UserData deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
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
    }
}
