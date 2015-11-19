package ru.delightfire.delight.utils;


import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by scaredChatsky on 19.11.2015.
 */
public class VolleySingleton {

    private static class VolleySingletonHolder{
        public static final VolleySingleton HOLDER_INSTANCE = new VolleySingleton();
    }

    public static VolleySingleton getInstance(){
        return VolleySingletonHolder.HOLDER_INSTANCE;
    }

    private static final String TAG = "VolleyPatterns";

    private static Context context = null;
    private RequestQueue requestQueue;

    public void setContext(Context contextToSet){
        this.context = contextToSet;
        requestQueue = Volley.newRequestQueue(context);
    }

    private <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);

        requestQueue.add(req);
    }

    private <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);

        requestQueue.add(req);
    }

    private void cancelPendingRequests(Object tag) {
        if (requestQueue != null) {
            requestQueue.cancelAll(tag);
        }
    }

    private void makeRequest(String url, HashMap<String, String> params){
        final JsonObjectRequest request = new JsonObjectRequest(JsonObjectRequest.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Response: ", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        });

        Log.d("Request: ", request.toString());
        addToRequestQueue(request);
    }
}
