package ru.delightfire.delight.utils;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import ru.delightfire.delight.entity.DelightTraining;
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

    private static final ParserJson jsonParser = new ParserJson();

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_TRAINING_ID = "training_id";
    private static final String TAG_NAME = "name";
    private static final String TAG_OWNER_LOGIN = "owner_login";
    private static final String TAG_TIME = "time";
    private static final String TAG_DAY_OF_WEEK = "dayOfWeek";
    private static final String TAG_AGENDA = "agenda";
    private static final String TAG_TRAINING = "training";

    public DelightTraining getTraining() throws ExecutionException, InterruptedException {

        DelightTraining training = new GetTraining().execute(1).get();
        return  training;
    }

    class GetTraining extends AsyncTask<Integer, DelightTraining, DelightTraining> {

        @Override
        protected DelightTraining doInBackground(Integer... trainingId) {
            DelightTraining training = null;

            String url = "http://delightfireapp.16mb.com/training_queries/get_training.php";

            HashMap<String, String> map = new HashMap<>();
            map.put(TAG_TRAINING_ID, trainingId[0].toString());

            int success;
            try {
                JSONObject json = jsonParser.makeRequestHttp(url, "GET", map);
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
    }

}
