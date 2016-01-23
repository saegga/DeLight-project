package ru.delightfire.delight.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import ru.delightfire.delight.entity.support.DelightPageInfo;
import ru.delightfire.delight.ui.adapter.EventAdapter;
import ru.delightfire.delight.ui.adapter.ViewPagerAdapter;
import ru.delightfire.delight.util.DelightEventDeserializer;
import ru.delightfire.delight.util.LoadingChecker;

/**
 * Created by sergei on 12.11.2015.
 */
public class ScheduleFragment extends Fragment {

    private List<DelightEvent> trainings = new ArrayList<>();
    private List<DelightEvent> performances = new ArrayList<>();
    private List<DelightEvent> meetings = new ArrayList<>();

    private FloatingActionButton fab;
    final LoadingChecker checker = new LoadingChecker(3);

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ViewPagerAdapter pagerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_schedule, container, false);

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

        tabLayout = (TabLayout) rootView.findViewById(R.id.tl_fragment_main);
        pagerAdapter = new ViewPagerAdapter(pages, getActivity());

        IconicsDrawable fabIcon = new IconicsDrawable(getActivity())
                .icon(FontAwesome.Icon.faw_plus)
                .color(getResources().getColor(R.color.white))
                .sizeDp(18);

        fab = (FloatingActionButton) rootView.findViewById(R.id.fab_activity_main);
        fab.setImageDrawable(fabIcon);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("fab::", "click");
            }
        });

        return rootView;
    }

    private void loadEvents(String queryParameter, final List<DelightEvent> events) {

        Ion.with(this)
                .load("POST", "http://delightfire-sunteam.rhcloud.com/app/androidQueries/get/get_all_events")
                .setBodyParameter("table", queryParameter)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        Log.d("Response::", result.toString());

                        if (result.get("success").getAsInt() == 1) {
                            Gson gson = new GsonBuilder()
                                    .registerTypeAdapter(DelightEvent.class, new DelightEventDeserializer())
                                    .create();

                            JsonArray eventsArray = result.get("events").getAsJsonArray();

                            for (int i = 0; i < eventsArray.size(); i++) {
                                events.add(gson.fromJson(eventsArray.get(i), DelightEvent.class));
                            }

                            Collections.sort(events);

                            if (checker.isLoaded()) {
                                initView();
                            }
                        }
                    }
                });
    }

    private void initView() {
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

}
