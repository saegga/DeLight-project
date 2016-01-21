package ru.delightfire.delight.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ru.delightfire.delight.R;
import ru.delightfire.delight.adapter.DelightPagerAdapter;
import ru.delightfire.delight.adapter.EventAdapter;
import ru.delightfire.delight.entity.DelightPageInfo;


/**
 * Created by sergei on 12.11.2015.
 */
public class MainFragment extends Fragment {

    private static final String TAG = "MainFragment";
    private ListView listViewEvent;
    private EventAdapter eventAdapter;
    public static final String FETCH_ALL_EVENTS = "http://delightfire-sunteam.rhcloud.com/app/androidQueries/get/get_all_events.php";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.vp_fragment_main);

        List<DelightPageInfo> pages = new ArrayList<>();
        View schedule = inflater.inflate(R.layout.element_schedule_page, viewPager, false);
        DelightPageInfo schedulePage = new DelightPageInfo("Расписание", schedule);
        pages.add(schedulePage);
        pages.add(schedulePage);

        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.tl_fragment_main);

        DelightPagerAdapter pagerAdapter = new DelightPagerAdapter(pages);

        viewPager.setAdapter(pagerAdapter);

        tabLayout.setupWithViewPager(viewPager);

        return rootView;
    }

    /*
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        Button btnSendMsg = (Button) view.findViewById(R.id.btnView_send_msg);
        final EditText messageTxt = (EditText) view.findViewById(R.id.inputView_msg);
        TabHost tabs = (TabHost) view.findViewById(android.R.id.tabhost);
        setHasOptionsMenu(true);
        tabs.setup();

        TabHost.TabSpec spec = tabs.newTabSpec("tag1");

        spec.setContent(R.id.events);
        spec.setIndicator("Расписание");
        tabs.addTab(spec);

        spec = tabs.newTabSpec("tag2");
        spec.setContent(R.id.chat);
        spec.setIndicator("Чат");
        tabs.addTab(spec);

        tabs.setCurrentTab(0);

        listViewEvent = (ListView) view.findViewById(R.id.events_list);
        ListView listMessages = (ListView) view.findViewById(R.id.listView_chat_messages);

         //eventAdapter = new EventAdapter(getActivity(), );
        //listViewEvent.setAdapter(adapter);


        ChatAdapter chatAdapter = new ChatAdapter(getActivity(), initMessage());
        listMessages.setAdapter(chatAdapter);

        btnSendMsg.setOnClickListener(new View.OnClickListener() {
            String msg = messageTxt.getText().toString();

            @Override
            public void onClick(View v) {
                sendMessage(msg);
            }
        });
        FloatingActionButton addActionBtn = (FloatingActionButton) view.findViewById(R.id.fab);
        addActionBtn.setClickable(true);

        addActionBtn.setOnClickListener(clickFab);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        initEvents(new IonCallback() {
            @Override
            public void onResult(List<DelightEvent> events) {
                eventAdapter = new EventAdapter(getActivity(), events);
                listViewEvent.setAdapter(eventAdapter);
            }
        });
    }*/

}
