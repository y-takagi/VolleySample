
package com.ytakagi.volleysample.network;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ytakagi.volleysample.SampleApplication;

public class RestClient {
    private static final String TAG = RestClient.class.getName();
    private static final String BASE_URL = "http://tab.do/api/1/";
    private static RequestQueue requestQueue = Volley.newRequestQueue(SampleApplication.getContext());
    public static ImageLoader imageLoader = new ImageLoader(requestQueue, new ImageCache() {
        private final int cacheSize = 10 * 1024 * 1024;
        private LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };

        public void putBitmap(String url, Bitmap bitmap) {
            cache.put(url, bitmap);
        }

        public Bitmap getBitmap(String url) {
            return cache.get(url);
        }
    });

    public static void execute(int method, String relativeUrl, Listener<String> successListener, ErrorListener errorListener) {
        StringRequest request = new StringRequest(method, BASE_URL + relativeUrl, successListener, errorListener);
        Log.v(TAG, request.getUrl());
        requestQueue.add(request);
        requestQueue.start();
    }

    public static void execute(String relativeUrl, Listener<String> successListener, ErrorListener errorListener) {
        execute(Method.GET, relativeUrl, successListener, errorListener);
    }
}
