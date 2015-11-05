package ru.delightfire.delight.activity;

import android.support.v4.app.Fragment;

import ru.delightfire.delight.FragmentActivityControll;
import ru.delightfire.delight.fragment.RegistrationFragmentMain;

/**
 * Created by sergei on 04.11.2015.
 */
public class LoginActivity extends FragmentActivityControll {
    @Override
    protected Fragment createFragment() {
        return new RegistrationFragmentMain();
    }
}
