package ru.delightfire.delight.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.delightfire.delight.R;
import ru.delightfire.delight.ui.adapter.EventAdapter;
import ru.delightfire.delight.ui.adapter.ViewPagerAdapter;
import ru.delightfire.delight.entity.subject.DelightEvent;
import ru.delightfire.delight.entity.support.DelightPageInfo;

/**
 * Created by sergei on 12.11.2015.
 */
public class MainFragment extends Fragment {

    private List<DelightEvent> trainings = new ArrayList<>();
    private List<DelightEvent> performances = new ArrayList<>();
    private List<DelightEvent> meetings = new ArrayList<>();

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ViewPagerAdapter pagerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

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

        tabLayout = (TabLayout) rootView.findViewById(R.id.tl_fragment_main);
        pagerAdapter = new ViewPagerAdapter(pages, getActivity());

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        return rootView;
    }

}
