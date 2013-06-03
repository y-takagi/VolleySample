
package com.ytakagi.volleysample.model;

import java.util.HashMap;
import java.util.Locale;

import lombok.Data;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.ytakagi.volleysample.network.RestClient;

@Data
public class Item {
    private int id;
    private String title;
    private String description;
    private HashMap<String, String>[] imageUrls;
    private User user;

    public static void popularItems(int page, int limit, Listener<String> successListener, ErrorListener errorListener) {
        String path = "items/popular.json";
        String params = String.format(Locale.getDefault(), "?page=%d&limit=%d", page, limit);
        RestClient.execute(path + params, successListener, errorListener);
    }
}
