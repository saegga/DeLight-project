package ru.delightfire.delight.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import ru.delightfire.delight.R;
import ru.delightfire.delight.fragment.FragmentActivityControll;
import ru.delightfire.delight.fragment.RegistrationKeyCheckFragment;

/**
 * Created by scaredChatsky on 06.11.2015.
 */
public class RegisterActivity extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Fragment fragment = new RegistrationKeyCheckFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.containerFragment, fragment)
                .commit();

    }
}
