package ru.delightfire.delight.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import ru.delightfire.delight.R;

/**
 * Created by sergei on 04.11.2015.
 */
public class RegistrationUserDataInputFragment extends Fragment {

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
        View view = inflater.inflate(R.layout.fragment_second_step, container, false);
        registerLogin = (EditText) view.findViewById(R.id.input_register_login);
        registerPassword = (EditText) view.findViewById(R.id.input_register_password);
        return view;
    }
}
