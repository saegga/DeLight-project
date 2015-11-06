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
public class RegistrationFragmentSecondStep extends Fragment {

    EditText nameReg;
    EditText lastNameReg;
    EditText loginReg;
    EditText phoneReg;
    Button btnRegOk;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second_step, container, false);
        nameReg = (EditText)view.findViewById(R.id.nameRegUser);
        lastNameReg = (EditText)view.findViewById(R.id.lastNameRegUser);
        loginReg = (EditText)view.findViewById(R.id.loginRegUser);
        phoneReg = (EditText)view.findViewById(R.id.phoneRegUser);
        btnRegOk = (Button)view.findViewById(R.id.btnRegOk);
        return view;
    }
}
