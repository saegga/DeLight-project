package ru.delightfire.delight.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import ru.delightfire.delight.R;
import ru.delightfire.delight.parser.ParserJson;

/**
 * Created by sergei on 04.11.2015.
 */

public class RegistrationFragmentOneStep extends Fragment {
    private static final String TAG = RegistrationFragmentOneStep.class.getSimpleName();
    private EditText inpKeyValue;
    private Button btnNextStep;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_key_check, container, false);
        inpKeyValue = (EditText)view.findViewById(R.id.edit_key_value);
        btnNextStep = (Button)view.findViewById(R.id.btnNextStep);

        btnNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = inpKeyValue.getText().toString();
                new CheckKey().execute(key);
            }
        });
        return view;
    }

    public class CheckKey extends AsyncTask<String, Void, Void>{
        ParserJson parserJson = new ParserJson();
        HashMap<String, String> map = new HashMap<>();
        private static final String URL = "http://192.168.224.1/Delight-project/DeLight-server/db_key_check.php";
        JSONObject object;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(String... params) {
            map.put("key", params[0]);
            try {
                 object = parserJson.makeRequestHttp(URL, "POST", map);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //Log.d(TAG, Arrays.asList(params) + "");
            //todo доделать запросы makeHttp сделать парсинг json
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.d(TAG, object.toString());
        }
    }
}
