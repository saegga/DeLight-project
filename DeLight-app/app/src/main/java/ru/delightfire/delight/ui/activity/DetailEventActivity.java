package ru.delightfire.delight.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;

import ru.delightfire.delight.R;
import ru.delightfire.delight.ui.fragment.DetailShowFragment;

/**
 * Created by sergei on 30.01.2016.
 */
public class DetailEventActivity extends AppCompatActivity {

    public static final String EXTRA_TYPE_EVENT = "extra_type_event";
    public static final String EXTRA_EVENT_ID = "extra_event_id";
    public static final String BUNDLE_EVENT_ID = "bundle_event_id";
    private int eventType;
    private int eventId;
   //todo пакуем event_id в аргумент
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event);

        eventId = getIntent().getIntExtra(EXTRA_EVENT_ID, -1) ;
        eventType = getIntent().getIntExtra(EXTRA_TYPE_EVENT, -1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_activity_detail);
        setSupportActionBar(toolbar);
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fl_activity_detail_event);
        if(fragment == null){
            fragment = instanceFragment(eventType);
            setArgument(fragment);
            manager.beginTransaction()
                    .add(R.id.fl_activity_detail_event, fragment)
                    .commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile_toolbar, menu);
        menu.getItem(0).setIcon(new IconicsDrawable(this)
                .icon(FontAwesome.Icon.faw_pencil_square_o)
                .colorRes(R.color.white)
                .sizeDp(20));
        return true;
    }
    public Fragment instanceFragment(int eventType){
        Fragment fragment = null;
        switch (eventType){
            case 0 : break;
            case 1 :
                fragment = new DetailShowFragment();
                break;
            case 2 : break;
        }
        return fragment;
    }
    public void setArgument(Fragment fragment){
        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_EVENT_ID, eventId);
        fragment.setArguments(bundle);
    }
}
