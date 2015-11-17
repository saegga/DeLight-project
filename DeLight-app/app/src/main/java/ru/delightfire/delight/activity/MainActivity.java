package ru.delightfire.delight.activity;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import ru.delightfire.delight.R;
import ru.delightfire.delight.fragment.MainTabFragment;
import ru.delightfire.delight.fragment.MyProfileFragment;
import ru.delightfire.delight.fragment.MySettingsFragment;
import ru.delightfire.delight.fragment.MySkillFragment;

/**
 * Created by scaredChatsky on 23.10.2015.
 */
public class MainActivity extends AppCompatActivity{

    private DrawerLayout drawerLayout;

    private NavigationView navigationView;

    private Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragmentContainerMain);
        if(fragment == null){
            fragment = new MainTabFragment();
            manager.beginTransaction()
                    .add(R.id.containerFragment, fragment)
                    .commit();
        }


        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new DrawerItemClick());
        //navigationView.setOnClickListener();
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home :
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.one : Log.d("one", "break"); break;
            case R.id.two : Log.d("two", "break"); break;
        }
        return super.onOptionsItemSelected(item);
    }

    private class DrawerItemClick implements NavigationView.OnNavigationItemSelectedListener{

        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()){
                case R.id.my_profile :
                    fragment = new MyProfileFragment();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;

                case R.id.my_settings :
                    fragment = new MySettingsFragment();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;

                case R.id.my_skill :
                    fragment = new MySkillFragment();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;

                default: break;
            }
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.containerFragment, fragment)
                    .commit();
            return true;
        }
    }
}
