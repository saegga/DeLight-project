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
import ru.delightfire.delight.utils.UserAccount;

public class LoginActivity extends AppCompatActivity {

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

                //Ion library for async query
                Ion.with(getApplicationContext())
                        .load("POST", "http://delightfireapp.16mb.com/app/androidQueries/auth/db_user_check.php")
                        .setBodyParameter("login", login)
                        .setBodyParameter("password", password)
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {
                                Log.d("Response:: ", result.toString());

                                DelightUser user = null;

                                if (result.get("success").getAsInt() == 1) {
                                    JsonObject userInfo = result.get("user").getAsJsonObject();
//                                    user = new DelightUser(login, password, userInfo.get("first_name").getAsString(),
//                                            userInfo.get("last_name").getAsString());
                                    user = new DelightUser(login, password);
                                }

                                if (user != null) {
                                    UserAccount.getInstance().saveUser(getApplicationContext(), user);
                                    redirectToMain();
                                }
                                ////TODO: Errors
                            }
                        });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sharedPreferences = getSharedPreferences(DelightUser.PREF_AUTH, MODE_PRIVATE);
        if(sharedPreferences.contains(DelightUser.PREF_LOGIN)){
            redirectToMain();
        }
    }

    private void redirectToMain() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
