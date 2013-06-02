
package com.ytakagi.volleysample.network;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ytakagi.volleysample.SampleApplication;

public class RestClient {
    private static final String TAG = RestClient.class.getName();
    private static final String BASE_URL = "http://tab.do/api/1/";
    private static RequestQueue requestQueue = Volley.newRequestQueue(SampleApplication.getContext());

    public static void execute(int method, String relativeUrl, Listener<String> successListener, ErrorListener errorListener) {
        StringRequest request = new StringRequest(method, BASE_URL + relativeUrl, successListener, errorListener);
        Log.v(TAG, request.getUrl());
        requestQueue.add(request);
        requestQueue.start();
    }
}
