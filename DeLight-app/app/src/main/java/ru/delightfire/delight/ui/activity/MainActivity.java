package ru.delightfire.delight.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import ru.delightfire.delight.R;
import ru.delightfire.delight.ui.fragment.MainFragment;
import ru.delightfire.delight.util.UserAccount;

/**
 * Created by scaredChatsky on 23.10.2015.
 */
public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_menu_black_24dp);

        setSupportActionBar(toolbar);

        new DrawerBuilder().withActivity(this).build();

        PrimaryDrawerItem scheduleItem = new PrimaryDrawerItem().withName(R.string.schedule);
        PrimaryDrawerItem profileItem = new PrimaryDrawerItem().withName(R.string.profile);
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
                        switch (position) {
                            case 0:
                                FragmentManager manager = getSupportFragmentManager();

                                Fragment mainFragment = new MainFragment();

                                manager.beginTransaction()
                                        .replace(R.id.fl_activity_main_content, mainFragment)
                                        .commit();
                                break;
                            case 3:
                                if (true)
                                    exit();
                                break;
                        }
                        return false;
                    }

                })
                .build();

        drawer.setSelectionAtPosition(0);

    }

    private void exit() {
        UserAccount.getInstance().deleteUser(this);
        Intent intent = new Intent(MainActivity.this, LaunchActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}
