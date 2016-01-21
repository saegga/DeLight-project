package ru.delightfire.delight.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import ru.delightfire.delight.R;
import ru.delightfire.delight.activity.MainActivity;
import ru.delightfire.delight.entity.DelightUser;

/**
 * Created by sergei on 04.11.2015.
 */
public class RegistrationFragment extends Fragment {

    private EditText login;
    private EditText password;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, container, false);

        login = (EditText) view.findViewById(R.id.acet_fragment_registration_input_login);
        password = (EditText) view.findViewById(R.id.acet_fragment_registration_input_password);

        AppCompatButton registrationConfirmButton = (AppCompatButton) view.findViewById(R.id.btn_fragment_registration_register_confirm);
        registrationConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ion.with(getActivity())
                        .load("POST", "http://delightfire-sunteam.rhcloud.com/app/androidQueries/create/db_user_create.php")
                        .setBodyParameter("login", login.getText().toString())
                        .setBodyParameter("password", password.getText().toString())
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {
                                DelightUser user = null;

                                if (result.get("success").getAsInt() == 1) {
                                    user = new DelightUser(login.getText().toString(),
                                            password.getText().toString());
                                }

                                if (user != null) {
                                    startActivity(new Intent(getContext(), MainActivity.class));
                                } else {
                                    Toast.makeText(getActivity(), "Не удалось зарегистрироваться", Toast.LENGTH_SHORT)
                                            .show();
                                }
                            }
                        });
            }
        });
        return view;
    }
}
