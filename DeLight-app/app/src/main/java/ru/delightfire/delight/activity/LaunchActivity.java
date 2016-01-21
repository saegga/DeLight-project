package ru.delightfire.delight.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import ru.delightfire.delight.R;
import ru.delightfire.delight.entity.DelightUser;
import ru.delightfire.delight.fragment.LoginFragment;

public class LaunchActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences sharedPreferences = getSharedPreferences(DelightUser.PREF_AUTH, MODE_PRIVATE);
        if (sharedPreferences.contains(DelightUser.PREF_LOGIN)) {
            redirectToMain();
        } else {
            FragmentManager manager = getSupportFragmentManager();

            Fragment loginFragment = new LoginFragment();

            manager.beginTransaction()
                    .replace()
        }
    }

    private void redirectToMain() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
