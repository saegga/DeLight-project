package ru.delightfire.delight.utils;

import android.app.Application;
import android.util.Log;

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
public class DelightContext{

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
        JsonParser jsonParser = new JsonParser();

        String url = "http://delightfireapp.16mb.com/auth_queries/db_user_check.php";

        HashMap<String, String> params = new HashMap<>();
        params.put(TAG_LOGIN, login);
        params.put(TAG_PASSWORD, password);

        JSONObject jsonObject = null;

        try {
            jsonObject = jsonParser.makeRequestHttp(url, "POST", params);
            Log.d("Response: ", jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            int success = jsonObject.getInt(TAG_SUCCESS);
            if (success == 1){
                user = new DelightUser(login, password, jsonObject.getString(TAG_FIRST_NAME), jsonObject.getString(TAG_LAST_NAME));
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
