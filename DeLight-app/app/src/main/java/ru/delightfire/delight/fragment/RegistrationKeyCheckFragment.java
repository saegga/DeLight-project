package ru.delightfire.delight.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import ru.delightfire.delight.R;
import ru.delightfire.delight.activity.RegisterActivity;

/**
 * Created by sergei on 04.11.2015.
 */

public class RegistrationKeyCheckFragment extends Fragment {

    private EditText inpKeyValue;

    private Button btnNextStep;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_key_check, container, false);

        inpKeyValue = (EditText) view.findViewById(R.id.input_regkey);
        btnNextStep = (Button) view.findViewById(R.id.btn_next_step);

        btnNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String key = inpKeyValue.getText().toString();

                Ion.with(getActivity())
                        .load("POST", "http://delightfireapp.16mb.com/auth_queries/db_key_check.php")
                        .setBodyParameter("key_value", key)
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {
                                Log.d("Response:: ", result.toString());

                                boolean success = false;

                                if (result.get("success").getAsInt() == 1) {
                                    if (result.get("key_status").getAsInt() == 1) {
                                        success = true;
                                    }
                                }

                                if (success) {
                                    RegisterActivity activity = (RegisterActivity) getActivity();
                                    activity.userDataSet();
                                } else {
                                    // TODO: 16.11.2015 Errors
                                }
                            }
                        });
            }
        });

        return view;
    }
}
