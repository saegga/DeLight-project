package ru.delightfire.delight.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.ExecutionException;

import ru.delightfire.delight.R;
import ru.delightfire.delight.entity.DelightUser;
import ru.delightfire.delight.utils.DelightContext;

public class LoginActivity extends AppCompatActivity {

    private DelightContext context = DelightContext.getInstance();

    public static final String PREF_AUTH = "prefs_auth";
    public static final String PREF_AUTH_STATE = "auth_state";
    private boolean isAuth;
    private static Context applicationContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button registerBtn = (Button) findViewById(R.id.btnRegistrationActivity);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });

        Button loginBtn = (Button) findViewById(R.id.btnAuth);

        final EditText inputEmail = (EditText) findViewById(R.id.input_login);
        final EditText inputPassword = (EditText) findViewById(R.id.input_password);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String login = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();

                DelightUser user = null;

                try {
                    user = new UserCheck().execute(login, password).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                if (user != null) {
                    SharedPreferences sharedPreferences = getSharedPreferences(PREF_AUTH, 0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(PREF_AUTH_STATE, true);
                    editor.commit();
                    redirectToMain();
                }
                ////TODO: Errors
            }
        });

        applicationContext = getApplicationContext();

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

    class UserCheck extends AsyncTask<String, Void, DelightUser> {

        @Override
        protected DelightUser doInBackground(String... userData) {
            DelightUser user = context.userCheck(userData[0], userData[1]);

            return user;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        /*
        protected void onPreExecute() {
            ProgressDialog dialog = new ProgressDialog(applicationContext);
            dialog.setMessage("Загружаюсь...");
            dialog.setIndeterminate(true);
            dialog.setCancelable(true);
            dialog.show();
            super.onPreExecute();
        }*/
    }
}
