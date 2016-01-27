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
import ru.delightfire.delight.ui.fragment.ProfileFragment;
import ru.delightfire.delight.ui.fragment.ScheduleFragment;
import ru.delightfire.delight.util.UserAccount;

/**
 * Created by scaredChatsky on 23.10.2015.
 */
public class MainActivity extends AppCompatActivity {

    private int currentDrawerPosition = 0;
    private int currentViewPagerPosition = 0;

    boolean hardReloadProfile = true;
    boolean hardReload = false;

    private Drawer drawer;

    public static final String DRAWER_POSITION = "position";
    public static final String VIEW_PAGER_POSITION = "view_pager_position";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_activity_main);

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

        drawer = new DrawerBuilder()
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
                        if (currentDrawerPosition != position || hardReload) {
                            switch (position) {
                                case 1:
                                    FragmentManager manager = getSupportFragmentManager();

                                    Fragment scheduleFragment = new ScheduleFragment();

                                    manager.beginTransaction()
                                            .replace(R.id.fl_activity_main_content, scheduleFragment)
                                            .commit();

                                    currentDrawerPosition = 1;
                                    break;
                                case 2:

                                    manager = getSupportFragmentManager();

                                    Fragment profileFragment = new ProfileFragment();

                                    manager.beginTransaction()
                                            .replace(R.id.fl_activity_main_content, profileFragment)
                                            .commit();
                                    currentDrawerPosition = 2;
                                    break;
                                case 4:
                                    if (true) {
                                        currentDrawerPosition = 4;
                                        exit();
                                    }
                                    break;
                            }
                        }
                        if (hardReload)
                            hardReload = !hardReload;

                        return false;
                    }

                })
                .build();

        drawer.setSelectionAtPosition(1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (resultCode == RESULT_CANCELED && data.getIntExtra(DRAWER_POSITION, currentDrawerPosition) != currentDrawerPosition) {
                int position = data.getIntExtra(DRAWER_POSITION, currentDrawerPosition);
                drawer.setSelectionAtPosition(position);
            } else if (resultCode == RESULT_OK) {
                hardReload = true;
                drawer.setSelectionAtPosition(currentDrawerPosition);
            }
            currentViewPagerPosition = data.getIntExtra(VIEW_PAGER_POSITION, currentViewPagerPosition);
        }


    }

    private void exit() {
        UserAccount.getInstance().deleteUser(getApplicationContext());
        Intent intent = new Intent(this, LaunchActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public int getCurrentViewPagerPosition() {
        return currentViewPagerPosition;
    }

    public void setCurrentViewPagerPosition(int currentViewPagerPosition) {
        this.currentViewPagerPosition = currentViewPagerPosition;
    }

    public boolean isHardReloadProfile() {
        return hardReloadProfile;
    }

    public void setHardReloadProfile(boolean hardReloadProfile) {
        this.hardReloadProfile = hardReloadProfile;
    }
}
