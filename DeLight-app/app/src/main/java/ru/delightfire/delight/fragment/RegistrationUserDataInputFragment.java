package ru.delightfire.delight.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.concurrent.ExecutionException;

import ru.delightfire.delight.R;
import ru.delightfire.delight.activity.MainActivity;
import ru.delightfire.delight.entity.DelightUser;
import ru.delightfire.delight.utils.DelightContext;

/**
 * Created by sergei on 04.11.2015.
 */
public class RegistrationUserDataInputFragment extends Fragment {

    private DelightContext context = DelightContext.getInstance();

    private EditText registerLogin;
    private EditText registerPassword;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_data_input, container, false);

        registerLogin = (EditText) view.findViewById(R.id.input_register_login);
        registerPassword = (EditText) view.findViewById(R.id.input_register_password);

        AppCompatButton registrationConfirmButton = (AppCompatButton) view.findViewById(R.id.btn_register_confirm);
        registrationConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DelightUser user = null;
                try {
                    user = new CreateUser().execute(registerLogin.getText().toString(), registerPassword.getText().toString()).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });

        return view;
    }

    class CreateUser extends AsyncTask<String, Void, DelightUser>{
        @Override
        protected DelightUser doInBackground(String... userData) {
            DelightUser user = context.createUser(userData[0], userData[1]);

            return user;
        }
    }

}
