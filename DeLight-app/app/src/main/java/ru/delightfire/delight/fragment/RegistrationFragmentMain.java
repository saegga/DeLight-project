package ru.delightfire.delight.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import ru.delightfire.delight.R;

/**
 * Created by sergei on 04.11.2015.
 */
public class RegistrationFragmentMain extends Fragment {
    EditText nameUser;
    EditText password;
    Button btnAuth;
    Button btnRegistration;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration_main,
                container, false);

        nameUser = (EditText)view.findViewById(R.id.nameUser);
        password = (EditText)view.findViewById(R.id.password);
        btnAuth = (Button)view.findViewById(R.id.btnAuth);
        btnRegistration = (Button)view.findViewById(R.id.btnRegistration);

        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            RegistrationFragmentOneStep one = new RegistrationFragmentOneStep();
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragmentContainerLogin, one);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }
}
