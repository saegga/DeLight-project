package ru.delightfire.delight.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.delightfire.delight.R;
import ru.delightfire.delight.entity.subject.DelightEvent;
import ru.delightfire.delight.entity.subject.DelightUser;
import ru.delightfire.delight.entity.support.DelightPageInfo;
import ru.delightfire.delight.ui.activity.AddEventActivity;
import ru.delightfire.delight.ui.activity.MainActivity;
import ru.delightfire.delight.ui.adapter.EventAdapter;
import ru.delightfire.delight.ui.adapter.ViewPagerAdapter;
import ru.delightfire.delight.util.ConditionsChecker;
import ru.delightfire.delight.util.DelightEventDeserializer;
import ru.delightfire.delight.util.DelightUserDeserializer;
import ru.delightfire.delight.util.UserAccount;

/**
 * Created by sergei on 12.11.2015.
 */
public class ScheduleFragment extends Fragment {

    private List<DelightEvent> trainings = new ArrayList<>();
    private List<DelightEvent> performances = new ArrayList<>();
    private List<DelightEvent> meetings = new ArrayList<>();

    final ConditionsChecker checker = new ConditionsChecker(4);

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ViewPagerAdapter pagerAdapter;
    private FloatingActionButton fab;

    private DelightUser user = UserAccount.getInstance().getUser(getActivity());

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager manager = getActivity().getSupportFragmentManager();
        Fragment fragment = new LoadingFragment();
        manager.beginTransaction()
                .add(R.id.fl_activity_main_content, fragment, "loading")
                .show(fragment)
                .commit();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_schedule, container, false);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Расписание");

        viewPager = (ViewPager) rootView.findViewById(R.id.vp_fragment_main);

        List<DelightPageInfo> pages = new ArrayList<>();

        EventAdapter trainingsAdapter = new EventAdapter(getActivity(), trainings);
        EventAdapter performancesAdapter = new EventAdapter(getActivity(), performances);
        EventAdapter meetingsAdapter = new EventAdapter(getActivity(), meetings);

        DelightPageInfo trainingsPage = new DelightPageInfo("Тренировки", trainingsAdapter);
        DelightPageInfo performancesPage = new DelightPageInfo("Выступления", performancesAdapter);
        DelightPageInfo meetingsPage = new DelightPageInfo("Встречи", meetingsAdapter);

        pages.add(trainingsPage);
        pages.add(performancesPage);
        pages.add(meetingsPage);

        loadEvents("current_trainings", trainings);
        loadEvents("shows", performances);
        loadEvents("meetings", meetings);

        loadUser();

        tabLayout = (TabLayout) rootView.findViewById(R.id.tl_fragment_main);
        pagerAdapter = new ViewPagerAdapter(pages, getActivity());

        fab = (FloatingActionButton) rootView.findViewById(R.id.fab_activity_main);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ((MainActivity) getActivity()).setCurrentViewPagerPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewPager.setCurrentItem(((MainActivity) getActivity()).getCurrentViewPagerPosition());

        return rootView;
    }

    private void loadUser() {

        final String userId = String.valueOf(user.getUserId());

        Ion.with(getActivity())
                .load("POST", "http://delightfire-sunteam.rhcloud.com/app/androidQueries/get/get_user")
                .setBodyParameter("user_id", userId)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if (e != null && result == null) {
                            new MaterialDialog.Builder(getActivity())
                                    .title(R.string.error)
                                    .content(R.string.check_connection)
                                    .backgroundColorRes(R.color.mainBackground)
                                    .positiveColorRes(R.color.white)
                                    .positiveText(R.string.ok)
                                    .show();
                        } else if (result.get("success").getAsInt() == 1) {

                            Gson gson = new GsonBuilder()
                                    .registerTypeAdapter(DelightUser.class, new DelightUserDeserializer(getActivity()))
                                    .create();

                            UserAccount.getInstance().setUser(gson.fromJson(result.get("user"), DelightUser.class));

                            ((MainActivity) getActivity()).setHardReloadProfile(false);

                            checker.wasComplete();
                            if (checker.isComplete()) {
                                initView();
                            }
                        }
                    }
                });
    }

    private void loadEvents(String queryParameter, final List<DelightEvent> events) {

        final String userId = String.valueOf(user.getUserId());

        Ion.with(this)
                .load("POST", "http://delightfire-sunteam.rhcloud.com/app/androidQueries/get/get_all_events")
                .setBodyParameter("table", queryParameter)
                .setBodyParameter("user_id", userId)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                                 @Override
                                 public void onCompleted(Exception e, JsonObject result) {
                                     if (e != null && result == null) {
                                         new MaterialDialog.Builder(getActivity())
                                                 .title(R.string.error)
                                                 .content(R.string.check_connection)
                                                 .backgroundColorRes(R.color.mainBackground)
                                                 .positiveColorRes(R.color.white)
                                                 .positiveText(R.string.ok)
                                                 .show();
                                     } else {
                                         if (result.get("success").getAsInt() == 1) {
                                             Gson gson = new GsonBuilder()
                                                     .registerTypeAdapter(DelightEvent.class, new DelightEventDeserializer())
                                                     .create();

                                             JsonArray eventsArray = result.get("events").getAsJsonArray();

                                             for (int i = 0; i < eventsArray.size(); i++) {
                                                 events.add(gson.fromJson(eventsArray.get(i), DelightEvent.class));
                                             }

                                             Collections.sort(events);
                                             
                                         } else if (result.get("success").getAsInt() == 0 && result.get("message").getAsString().equals("empty result")) {

                                         }

                                         checker.wasComplete();
                                         if (checker.isComplete()) {
                                             initView();
                                         }
                                     }
                                 }
                             }

                );
    }

    private void initView() {
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        FragmentManager manager = getActivity().getSupportFragmentManager();
        Fragment fragment = manager.findFragmentByTag("loading");
        manager.beginTransaction()
                .hide(fragment)
                .remove(fragment)
                .commit();

        viewPager.setCurrentItem(((MainActivity) getActivity()).getCurrentViewPagerPosition(), true);

        if (UserAccount.getInstance().hasAddEventPermission()) {

            IconicsDrawable fabIcon = new IconicsDrawable(getActivity())
                    .icon(FontAwesome.Icon.faw_plus)
                    .colorRes(R.color.white)
                    .sizeDp(18);

            fab.setImageDrawable(fabIcon);

            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), AddEventActivity.class);
                    int position = viewPager.getCurrentItem();
                    intent.putExtra(MainActivity.VIEW_PAGER_POSITION, position);
                    startActivityForResult(intent, position);
                }
            });
        } else {
            fab.hide();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
