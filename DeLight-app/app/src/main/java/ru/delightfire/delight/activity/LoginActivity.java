package ru.delightfire.delight.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import ru.delightfire.delight.R;

/**
 * Created by sergei on 04.11.2015.
 */
public class LoginActivity extends Activity {
//todo сделать фрагмент регистрации
    EditText nameUser;
    EditText password;
    Button btnAuth;
    Button btnRegistration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        nameUser = (EditText) findViewById(R.id.nameUser);
        password = (EditText) findViewById(R.id.password);
        btnAuth = (Button) findViewById(R.id.btnAuth);
        btnRegistration = (Button) findViewById(R.id.btnRegistration);
    }
}
