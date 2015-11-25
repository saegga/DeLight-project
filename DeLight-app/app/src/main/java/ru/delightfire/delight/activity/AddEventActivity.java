package ru.delightfire.delight.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import ru.delightfire.delight.R;
import ru.delightfire.delight.fragment.AddMeetFragment;
import ru.delightfire.delight.fragment.AddShowFragment;

/**
 * Created by sergei on 18.11.2015.
 */
public class AddEventActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup group;
    private RadioButton btnPerformance;
    private RadioButton btnMeet;
    private RadioButton btnTraining;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private AddShowFragment performanceFragment;
    private AddMeetFragment meetFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        group = (RadioGroup)(findViewById(R.id.group_choose));
        btnPerformance = (RadioButton) findViewById(R.id.btn_performance);
        btnMeet = (RadioButton) findViewById(R.id.btn_meet);
        btnTraining = (RadioButton) findViewById(R.id.btn_training);
        group.setOnCheckedChangeListener(this);
        manager = getSupportFragmentManager();
        performanceFragment = new AddShowFragment();
        meetFragment = new AddMeetFragment();

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        transaction = manager.beginTransaction();
        switch (checkedId){
            case  R.id.btn_performance :
                if(manager.getFragments() != null){
                    Log.d("AddEventActivity", "replace");
                    transaction.replace(R.id.add_event_container , performanceFragment);
                }else{
                    Log.d("AddEventActivity", "add");
                    transaction.add(R.id.add_event_container , performanceFragment);
                }
                break;
            case  R.id.btn_meet :
                if(manager.getFragments() != null){
                    Log.d("AddEventActivity", "replace");
                    transaction.replace(R.id.add_event_container , meetFragment);
                }else{
                    Log.d("AddEventActivity", "add");
                    transaction.add(R.id.add_event_container , meetFragment);
                }
                Log.d("AddEventActivity", "check" + checkedId );
                break;
            case  R.id.btn_training : Log.d("AddEventActivity", "check" + checkedId);break;
        }
        transaction.commit();
    }
}
