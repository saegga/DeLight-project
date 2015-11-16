package ru.delightfire.delight.fragment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.ExecutionException;

import ru.delightfire.delight.R;
import ru.delightfire.delight.utils.DelightContext;

/**
 * Created by sergei on 04.11.2015.
 */

public class RegistrationKeyCheckFragment extends Fragment {

    private DelightContext context = DelightContext.getInstance();

    private static final String TAG = RegistrationKeyCheckFragment.class.getSimpleName();
    private EditText inpKeyValue;
    private Button btnNextStep;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_key_check, container, false);

        inpKeyValue = (EditText)view.findViewById(R.id.input_regkey);
        btnNextStep = (Button)view.findViewById(R.id.btnNextStep);

        btnNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = inpKeyValue.getText().toString();
                try {
                    if(new KeyCheck().execute(key).get()){

                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        return view;
    }

    class KeyCheck extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... key) {
            Boolean freeKey = context.keyCheck(key[0]);

            return freeKey;
        }

        @Override
        protected void onPreExecute() {
            ProgressDialog dialog = new ProgressDialog(getContext());
            dialog.setMessage("Загружаюсь...");
            dialog.setIndeterminate(true);
            dialog.setCancelable(true);
            dialog.show();
            super.onPreExecute();
        }
    }

}
