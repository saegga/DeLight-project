package ru.delightfire.delight.activity;

import android.content.Intent;
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

    DelightContext context = DelightContext.getInstance();

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

                if(user != null) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
                ////TODO: Errors
            }
        });
    }

    class UserCheck extends AsyncTask<String, Void, DelightUser> {

        @Override
        protected DelightUser doInBackground(String... userData) {
            DelightUser user = context.userCheck(userData[0], userData[1]);

            return user;
        }

        /*@Override
        protected void onPreExecute() {
            ProgressDialog dialog = new ProgressDialog(getApplicationContext());
            dialog.setMessage("Загружаюсь...");
            dialog.setIndeterminate(true);
            dialog.setCancelable(true);
            dialog.show();
            super.onPreExecute();
        }*/
    }

}
