package ru.delightfire.delight.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import ru.delightfire.delight.R;
import ru.delightfire.delight.entity.DelightUser;
import ru.delightfire.delight.utils.DelightContext;

public class LoginActivity extends AppCompatActivity {

    private DelightContext context = DelightContext.getInstance();

    public static final String PREF_AUTH = "prefs_auth";
    public static final String PREF_AUTH_STATE = "auth_state";
    private boolean isAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button registerBtn = (Button) findViewById(R.id.btnRegistrationActivity);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
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

                final DelightUser[] user = {null};

                //Ion library for async query
                Ion.with(getApplicationContext())
                        .load("POST", "http://delightfireapp.16mb.com/auth_queries/db_user_check.php")
                        .setBodyParameter("login", login)
                        .setBodyParameter("password", password)
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {
                                Log.d("Response:: ", result.toString());

                                if (result.get("success").getAsInt() == 1) {
                                    JsonObject userInfo = result.get("user").getAsJsonObject();
                                    user[0] = new DelightUser(login, password, userInfo.get("first_name").getAsString(),
                                            userInfo.get("last_name").getAsString());
                                }

                                if (user[0] != null) {
                                    SharedPreferences sharedPreferences = getSharedPreferences(PREF_AUTH, 0);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putBoolean(PREF_AUTH_STATE, true);
                                    editor.commit();
                                    redirectToMain();
                                }
                            }
                        });

                if (user[0] != null) {
                    SharedPreferences sharedPreferences = getSharedPreferences(PREF_AUTH, 0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(PREF_AUTH_STATE, true);
                    editor.commit();
                    redirectToMain();
                }
                ////TODO: Errors
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sharedPreferences = getSharedPreferences(PREF_AUTH, 0);
        isAuth = sharedPreferences.getBoolean(PREF_AUTH_STATE, false);
        if (isAuth)
            redirectToMain();
    }

    private void redirectToMain() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
