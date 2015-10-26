package ru.delightfire.delight.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.List;

import ru.delightfire.delight.R;
import ru.delightfire.delight.adapter.DelightTrainingAdapter;
import ru.delightfire.delight.entity.DelightTraining;

/**
 * Created by scaredChatsky on 23.10.2015.
 */
public class MainActivity extends Activity{

    List<DelightTraining> trainings;
    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tabs = (TabHost) findViewById(android.R.id.tabhost);

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

        listView = (ListView) findViewById(R.id.events_list);

        DelightTrainingAdapter adapter = new DelightTrainingAdapter(this, initEvents());

        listView.setAdapter(adapter);
    }

    private List<DelightTraining> initEvents(){
        trainings = new ArrayList<DelightTraining>();

        DelightTraining first = new DelightTraining("admin", "first");

        trainings.add(first);

        return trainings;
    }

}
