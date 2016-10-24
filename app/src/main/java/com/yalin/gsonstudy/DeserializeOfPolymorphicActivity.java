package com.yalin.gsonstudy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.yalin.gsonstudy.typeadapter.RuntimeTypeAdapterFactory;

import java.lang.reflect.Type;
import java.util.List;

/**
 * 作者：YaLin
 * 日期：2016/10/24.
 */

public class DeserializeOfPolymorphicActivity extends AppCompatActivity {
    private static final String TAG = "Polymorphic";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_button);
    }

    public void start(View view) {
        String animalJson = "[{'name':'animal1','type':'animalType'},{'name':'animal2','type':'animalType'},{'playsCatch':true,'name':'dog1','type':'dogType'},{'playsCatch':false,'name':'dog2','type':'dogType'},{'chasesRedLaserDot':false,'name':'cat1','type':'catType'},{'chasesRedLaserDot':false,'name':'cat2','type':'catType'}]";

        Gson gson = new Gson();
        Type animalType = new TypeToken<List<Animal>>() {
        }.getType();

        // general deserialize
        List<Animal> animalList = gson.fromJson(animalJson, animalType);
        Log.d(TAG, "animalList = " + animalList);

        RuntimeTypeAdapterFactory typeFactory = RuntimeTypeAdapterFactory
                .of(Animal.class, "type")
                .registerSubtype(Animal.class, "animalType")
                .registerSubtype(Dog.class, "dogType")
                .registerSubtype(Cat.class, "catType");

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(typeFactory);
        animalList = gsonBuilder.create().fromJson(animalJson, animalType);
        Log.d(TAG, "animalList = " + animalList);
    }

    private static class Animal {
        protected String name;

        @Override
        public String toString() {
            return "Animal{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    private static class Dog extends Animal {
        boolean playsCatch;

        @Override
        public String toString() {
            return "Dog{" +
                    super.toString() +
                    "playsCatch=" + playsCatch +
                    '}';
        }
    }

    private static class Cat extends Animal {
        boolean chasesRedLaserDot;

        @Override
        public String toString() {
            return "Cat{" +
                    super.toString() +
                    "chasesRedLaserDot=" + chasesRedLaserDot +
                    '}';
        }
    }
}
