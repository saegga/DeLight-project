package ru.delightfire.delight.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;

import ru.delightfire.delight.R;

/**
 * Created by sergei on 04.11.2015.
 */
public abstract class FragmentActivityControll extends FragmentActivity {

    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragmentContainerRegister);

        if(fragment == null){
            fragment = createFragment();
            manager.beginTransaction()
                    .add(R.id.fragmentContainerRegister, fragment)
                    .commit();
        }
    }
}
