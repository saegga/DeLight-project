package ru.delightfire.delight.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import ru.delightfire.delight.R;
import ru.delightfire.delight.ui.fragment.AddMeetingFragment;
import ru.delightfire.delight.ui.fragment.AddShowFragment;
import ru.delightfire.delight.ui.fragment.AddTrainingFragment;
import ru.delightfire.delight.ui.fragment.ScheduleFragment;
import ru.delightfire.delight.util.UserAccount;

/**
 * Created by sergei on 18.11.2015.
 */
public class AddEventActivity extends AppCompatActivity {

    private int currentPosition = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_event);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_activity_add_event);
        setSupportActionBar(toolbar);

        new DrawerBuilder().withActivity(this).build();

        PrimaryDrawerItem scheduleItem = new PrimaryDrawerItem()
                .withName(R.string.schedule)
                .withIcon(FontAwesome.Icon.faw_list);
        PrimaryDrawerItem profileItem = new PrimaryDrawerItem()
                .withName(R.string.profile)
                .withIcon(FontAwesome.Icon.faw_user);
        DividerDrawerItem dividerItem = new DividerDrawerItem();
        SecondaryDrawerItem exitItem = new SecondaryDrawerItem().withName(R.string.exit);

        View header = getLayoutInflater().inflate(R.layout.element_drawer_header, null);
        TextView userName = (TextView) header.findViewById(R.id.user_name);
        userName.setText(UserAccount.getInstance().getUser(this).getLogin());

        Drawer drawer = new DrawerBuilder()
                .withActivity(this)
                .withHeader(header)
                .withSelectedItem(0)
                .withToolbar(toolbar)
                .addDrawerItems(
                        scheduleItem,
                        profileItem,
                        dividerItem,
                        exitItem
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (currentPosition != position) {
                            switch (position) {
                                case 1:
                                    FragmentManager manager = getSupportFragmentManager();

                                    Fragment scheduleFragment = new ScheduleFragment();

                                    manager.beginTransaction()
                                            .replace(R.id.fl_activity_main_content, scheduleFragment)
                                            .commit();
                                    currentPosition = 1;
                                    break;
                                case 4:
                                    if (true) {
                                        currentPosition = 4;
                                        exit();
                                    }
                                    break;
                            }
                        }
                        return false;
                    }

                })
                .build();

        drawer.setSelectionAtPosition(1);

        int request = getIntent().getIntExtra("attach", -1);
        Fragment fragment = null;

        switch (request) {
            case 0:
                fragment = new AddTrainingFragment();
                break;
            case 1:
                fragment = new AddShowFragment();
                break;
            case 2:
                fragment = new AddMeetingFragment();
                break;
        }

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.fl_activity_add_event_content, fragment)
                .commit();
    }

    private void exit() {
        UserAccount.getInstance().deleteUser(this);
        Intent intent = new Intent(this, LaunchActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}