package ru.delightfire.delight.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import ru.delightfire.delight.R;
import ru.delightfire.delight.ui.fragment.DetailEventFragment;

/**
 * Created by sergei on 30.01.2016.
 */
public class DetailEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_activity_detail);
        setSupportActionBar(toolbar);
        FragmentManager manager = getSupportFragmentManager();
        Fragment detailFragment = manager.findFragmentById(R.id.fl_activity_detail_event);
        if(detailFragment == null){
            detailFragment = new DetailEventFragment();
            manager.beginTransaction()
                    .add(R.id.fl_activity_detail_event, detailFragment)
                    .commit();
        }

    }

}
