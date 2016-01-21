package ru.delightfire.delight.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import ru.delightfire.delight.entity.DelightUser;
import ru.delightfire.delight.utils.UserAccount;

/**
 * Created by scaredChatsky on 21.01.2016.
 */
public class LoginFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_register, container, false);

        final Button registerBtn = (Button) rootView.findViewById(R.id.btnRegistrationActivity);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getActivity().getSupportFragmentManager();


            }
        });

        final EditText inputEmail = (EditText) findViewById(R.id.input_login);
        final EditText inputPassword = (EditText) findViewById(R.id.input_password);

        Button loginBtn = (Button) findViewById(R.id.btnAuth);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String login = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                //Ion library for async query
                Ion.with(getApplicationContext())
                        .load("POST", "http://delightfire-sunteam.rhcloud.com/app/androidQueries/auth/db_user_check")
                        .setBodyParameter("login", login)
                        .setBodyParameter("password", password)
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {

                                if (e != null) {
                                    Log.d("Login acticity: ", e.getMessage());
                                    return;
                                }

                                Log.d("Response:: ", result.toString());

                                if (result.get("success").getAsInt() == 1) {
                                    DelightUser user = new DelightUser(login, password);
                                    UserAccount.getInstance().saveUser(getApplicationContext(), user);
                                    redirectToMain();
                                }
                            }
                        });
            }
        });

        return rootView;
    }
}
