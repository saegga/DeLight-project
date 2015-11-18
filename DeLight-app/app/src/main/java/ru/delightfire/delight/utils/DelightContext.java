package ru.delightfire.delight.utils;

import android.app.Application;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.HashMap;

import ru.delightfire.delight.entity.DelightTraining;
import ru.delightfire.delight.entity.DelightUser;
import ru.delightfire.delight.parser.JsonParser;

/**
 * Created by scaredChatsky on 07.11.2015.
 */
public class DelightContext extends Application{

    private static class DelightContextHolder{
        public static final DelightContext HOLDER_INSTANCE = new DelightContext();
    }

    public static DelightContext getInstance(){
        return DelightContextHolder.HOLDER_INSTANCE;
    }

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_TRAINING_ID = "training_id";
    private static final String TAG_NAME = "name";
    private static final String TAG_OWNER_LOGIN = "owner_login";
    private static final String TAG_TIME = "time";
    private static final String TAG_DAY_OF_WEEK = "dayOfWeek";
    private static final String TAG_AGENDA = "agenda";
    private static final String TAG_TRAINING = "training";
    private static final String TAG_REGKEY = "key_value";
    private static final String TAG_REGKEY_STATUS = "key_status";
    private static final String TAG_PASSWORD = "password";
    private static final String TAG_LOGIN = "login";
    private static final String TAG_USER = "user";
    private static final String TAG_FIRST_NAME = "first_name";
    private static final String TAG_LAST_NAME = "last_name";

    private static final String TAG = "VolleyPatterns";

    private static Context context = null;
    private RequestQueue requestQueue;

    public void setContext(Context contextToSet){
        this.context = contextToSet;
        requestQueue = Volley.newRequestQueue(context);
    }

    private  <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);

        requestQueue.add(req);
    }

    private  <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);

        requestQueue.add(req);
    }

    private void cancelPendingRequests(Object tag) {
        if (requestQueue != null) {
            requestQueue.cancelAll(tag);
        }
    }

    private JSONObject makeRequest(String url, HashMap<String, String> params){
        final JSONObject[] jsonObject = new JSONObject[1];
        final JsonObjectRequest request = new JsonObjectRequest(JsonObjectRequest.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Response: ", response.toString());
                jsonObject[0] = response;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        });

        Log.d("Request: ", request.toString());
        addToRequestQueue(request);

        return jsonObject[0];
    }


    public DelightTraining getTraining(Integer trainingId) {

        JsonParser jsonParser = new JsonParser();
        DelightTraining training = null;

        String url = "http://delightfireapp.16mb.com/training_queries/get_training.php";

        HashMap<String, String> map = new HashMap<>();
        map.put(TAG_TRAINING_ID, trainingId.toString());

        int success;
        try {
            JSONObject json = jsonParser.makeRequestHttp(url, "POST", map);
            Log.d("Training: ", json.toString());
            success = json.getInt(TAG_SUCCESS);

            if (success == 1) {
                JSONArray trainingObj = json.getJSONArray(TAG_TRAINING);
                JSONObject trainingJsonObj = trainingObj.getJSONObject(0);
                training = new DelightTraining(trainingJsonObj.getString(TAG_AGENDA), trainingJsonObj.getString(TAG_OWNER_LOGIN), trainingJsonObj.getString(TAG_NAME));
            }else{

            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return training;
    }

    public DelightUser userCheck(String login, String password){
        DelightUser user = null;

        String url = "http://delightfireapp.16mb.com/auth_queries/db_user_check.php";

        HashMap<String, String> params = new HashMap<>();
        params.put(TAG_LOGIN, login);
        params.put(TAG_PASSWORD, password);

        JSONObject response = makeRequest(url, params);

        try {
            int success = response.getInt(TAG_SUCCESS);
            if (success == 1){
                user = new DelightUser(login, password, response.getString(TAG_FIRST_NAME), response.getString(TAG_LAST_NAME));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return user;
    }

    public boolean keyCheck(String key) {

        JsonParser jsonParser = new JsonParser();
        boolean freeKey = false;

        String url = "http://delightfireapp.16mb.com/auth_queries/db_key_check.php";

        HashMap<String, String> map = new HashMap<>();
        map.put(TAG_REGKEY, key.toString());

        int success;
        try {
            JSONObject json = jsonParser.makeRequestHttp(url, "POST", map);
            Log.d("Regkey: ", json.toString());
            success = json.getInt(TAG_SUCCESS);

            if (success == 1) {
                freeKey = json.getInt(TAG_REGKEY_STATUS) == 1;

            }else{

            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return freeKey;
    }

    public DelightUser createUser(String login, String password){
        DelightUser user = null;
        JsonParser jsonParser = new JsonParser();

        String url = "http://delightfireapp.16mb.com/auth_queries/db_user_create.php";

        HashMap<String, String> map = new HashMap<>();
        map.put(TAG_LOGIN, login);
        map.put(TAG_PASSWORD, password);

        int success;
        try {
            Log.d("Map: ", map.toString());
            JSONObject json = jsonParser.makeRequestHttp(url, "POST", map);
            Log.d("Response: ", json.toString());
            success = json.getInt(TAG_SUCCESS);

            if (success == 1) {
                user = this.userCheck(login, password);
            }else{

            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return user;
    }



}
