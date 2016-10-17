package com.yalin.gsonstudy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 作者：YaLin
 * 日期：2016/10/17.
 */

public class MappingOfMapActivity extends AppCompatActivity {
    private static final String TAG = "MappingOfMapActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_button);
    }

    public void start(View view) {
        Gson gson = new Gson();

        HashMap<String, List<String>> employees = new HashMap<>();
        employees.put("A", Arrays.asList("Andreas", "Arnold", "Aden"));
        employees.put("C", Arrays.asList("Christian", "Carter"));
        employees.put("M", Arrays.asList("Marcus", "Mary"));

        // map serialization
        String employeesJson = gson.toJson(employees);
        Log.d(TAG, "employeesJson = " + employeesJson);

        // map deserialization
        String dollarJson = "{ '1$': { 'amount': 1, 'currency': 'Dollar'}, '2$': { 'amount': 2, 'currency': 'Dollar'}, '3€': { 'amount': 3, 'currency': 'Euro'} }";
        Type amountCurrencyType =
                new TypeToken<HashMap<String, AmountWithCurrency>>() {
                }.getType();

        HashMap<String, AmountWithCurrency> amountCurrency =
                gson.fromJson(dollarJson, amountCurrencyType);
        Log.d(TAG, "amountCurrency = " + amountCurrency);
    }

    static class AmountWithCurrency {
        String currency;
        int amount;

        @Override
        public String toString() {
            return "AmountWithCurrency{" +
                    "currency='" + currency + '\'' +
                    ", amount=" + amount +
                    '}';
        }
    }
}
