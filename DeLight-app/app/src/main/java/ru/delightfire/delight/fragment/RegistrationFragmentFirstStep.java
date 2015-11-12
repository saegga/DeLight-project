package ru.delightfire.delight.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import ru.delightfire.delight.R;

/**
 * Created by sergei on 04.11.2015.
 */

public class RegistrationFragmentFirstStep extends Fragment {
    private static final String TAG = RegistrationFragmentFirstStep.class.getSimpleName();
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

        @Override
        protected Void doInBackground(String... params) {
            //Log.d(TAG, Arrays.asList(params) + "");
            //todo доделать запросы потестить makeHttp сделать парсинг json
            return null;
        }
    }

}
