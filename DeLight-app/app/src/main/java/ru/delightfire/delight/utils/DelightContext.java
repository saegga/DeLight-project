package ru.delightfire.delight.utils;

import android.util.Log;

import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ru.delightfire.delight.entity.DelightTraining;
import ru.delightfire.delight.entity.DelightUser;
import ru.delightfire.delight.parser.ParserJson;

/**
 * Created by scaredChatsky on 07.11.2015.
 */
public class DelightContext {

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


    public DelightTraining getTraining(Integer trainingId) {

        ParserJson jsonParser = new ParserJson();
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
        ParserJson jsonParser = new ParserJson();
        DelightUser user = null;

        String url = "http://delightfireapp.16mb.com/auth_queries/db_user_check.php";

        HashMap<String, String> map = new HashMap<>();
        map.put(TAG_LOGIN, login);
        map.put(TAG_PASSWORD, password);

        int success;
        try {
            Log.d("Map: ", map.toString());
            JSONObject json = jsonParser.makeRequestHttp(url, "POST", map);
            Log.d("User: ", json.toString());
            success = json.getInt(TAG_SUCCESS);

            if (success == 1) {
                JSONArray userObj = json.getJSONArray(TAG_USER);
                JSONObject userJsonObj = userObj.getJSONObject(0);
                user = new DelightUser(login, password, userJsonObj.getString(TAG_FIRST_NAME), userJsonObj.getString(TAG_LAST_NAME));
            }else{

            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return user;
    }

    public boolean keyCheck(String key) {

        ParserJson jsonParser = new ParserJson();
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

    @Deprecated
    public void serverFullResponse(String url, String key, String value){
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost http = new HttpPost(url);
        List nameValuePairs = new ArrayList();
        nameValuePairs.add(new BasicNameValuePair(key, value));
        try {
            http.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            String response = httpclient.execute(http, new BasicResponseHandler());
            Log.d("Response: ", response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
