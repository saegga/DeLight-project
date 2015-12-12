package ru.delightfire.delight.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import ru.delightfire.delight.R;
import ru.delightfire.delight.fragment.RegistrationUserDataInputFragment;

/**
 * Created by scaredChatsky on 06.11.2015.
 */
public class RegisterActivity extends AppCompatActivity{

    final private FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Fragment fragment = new RegistrationUserDataInputFragment();
        fragmentManager.beginTransaction()
                .add(R.id.fragmentContainerMain, fragment)
                .commit();

    }
}
