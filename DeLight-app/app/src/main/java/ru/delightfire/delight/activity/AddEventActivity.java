package ru.delightfire.delight.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import ru.delightfire.delight.R;
import ru.delightfire.delight.fragment.AddMeetFragment;
import ru.delightfire.delight.fragment.AddShowFragment;
import ru.delightfire.delight.fragment.AddTrainingFragment;

/**
 * Created by sergei on 18.11.2015.
 */
public class AddEventActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    public static final String FRAGMENT_ADD_SAVE_STATE = "fragment_add_save_state";
    private RadioGroup group;
    private RadioButton btnPerformance;
    private RadioButton btnMeet;
    private RadioButton btnTraining;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private AddShowFragment performanceFragment;
    private AddMeetFragment meetFragment;
    Fragment actionFragment;
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


    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        transaction = manager.beginTransaction();
        switch (checkedId){
            case  R.id.btn_performance :
                actionFragment = new AddShowFragment();
                createActionFragment();
                break;
            case  R.id.btn_meet :
                actionFragment = new AddMeetFragment();
                createActionFragment();
                Log.d("AddEventActivity", "check" + checkedId );
                break;
            case  R.id.btn_training :
                actionFragment = new AddTrainingFragment();
                createActionFragment();
                Log.d("AddEventActivity", "check" + checkedId);
                break;
        }
        transaction.commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if(actionFragment != null){
            manager.putFragment(outState, FRAGMENT_ADD_SAVE_STATE, actionFragment);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        //super.onRestoreInstanceState(savedInstanceState);
        actionFragment = manager.getFragment(savedInstanceState, FRAGMENT_ADD_SAVE_STATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(actionFragment != null){
            transaction = manager.beginTransaction();
            transaction.replace(R.id.add_event_container , actionFragment);
            transaction.commit();
        }
    }
    private void createActionFragment(){
        if(manager.getFragments() != null){
            //Log.d("AddEventActivity", "replace");
            transaction.replace(R.id.add_event_container , actionFragment);
        }else{
            //Log.d("AddEventActivity", "add");
            transaction.add(R.id.add_event_container , actionFragment);
        }
    }
}
