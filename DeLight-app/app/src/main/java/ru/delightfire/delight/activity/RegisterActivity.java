package ru.delightfire.delight.activity;

import android.app.Activity;
import android.support.v4.app.Fragment;

import ru.delightfire.delight.fragment.FragmentActivityControll;
import ru.delightfire.delight.fragment.RegistrationFragmentFirstStep;

/**
 * Created by scaredChatsky on 06.11.2015.
 */
public class RegisterActivity extends FragmentActivityControll{
    @Override
    protected Fragment createFragment() {
        return new RegistrationFragmentFirstStep();
    }

}
