package ru.delightfire.delight.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import ru.delightfire.delight.R;
import ru.delightfire.delight.fragment.MainTabFragment;
import ru.delightfire.delight.fragment.MyProfileFragment;
import ru.delightfire.delight.fragment.MySettingsFragment;
import ru.delightfire.delight.fragment.MySkillFragment;
import ru.delightfire.delight.utils.UserAccount;

/**
 * Created by scaredChatsky on 23.10.2015.
 */
public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    private NavigationView navigationView;

    private Toolbar toolbar;
    private TextView nameDrawer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragmentContainerMain);
        if (fragment == null) {
            fragment = new MainTabFragment();
            manager.beginTransaction()
                    .add(R.id.containerFragment, fragment)
                    .commit();
        }

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new DrawerItemClick());
        View headerView = navigationView.inflateHeaderView(R.layout.element_header_drawer);
        nameDrawer = (TextView) headerView.findViewById(R.id.drawer_name);
        nameDrawer.setText(UserAccount.getInstance().getLoginUser(this));
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_menu_black_24dp);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class DrawerItemClick implements NavigationView.OnNavigationItemSelectedListener {

        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.my_profile:
                    fragment = new MyProfileFragment();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case R.id.my_settings:
                    fragment = new MySettingsFragment();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;

                case R.id.my_skill:
                    fragment = new MySkillFragment();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case R.id.my_main_tab:
                    fragment = new MainTabFragment();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case R.id.exit:
                    exit();
                    return true;
                default:
                    break;
            }
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.containerFragment, fragment)
                    .commit();
            return true;
        }
    }

    private void exit() {
        UserAccount.getInstance().deleteUser(this);
        Intent intent = new Intent(MainActivity.this, LaunchActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
