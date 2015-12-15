package ru.delightfire.delight.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.List;

import ru.delightfire.delight.R;
import ru.delightfire.delight.activity.AddEventActivity;
import ru.delightfire.delight.adapter.ChatAdapter;
import ru.delightfire.delight.adapter.TrainingAdapter;
import ru.delightfire.delight.entity.ChatMessage;
import ru.delightfire.delight.entity.DelightTraining;


/**
 * Created by sergei on 12.11.2015.
 */
public class MainTabFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.element_tab_host, container, false);

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

        ListView listView = (ListView) view.findViewById(R.id.events_list);
        ListView listMessages = (ListView) view.findViewById(R.id.listView_chat_messages);

        TrainingAdapter adapter = new TrainingAdapter(getActivity(), initEvents());
        listView.setAdapter(adapter);


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


    @Deprecated
    private void sendMessage(String msg) {

    }

    //TODO: Инициализация всех событий
    private List<DelightTraining> initEvents() {
        List<DelightTraining> trainings = new ArrayList<>();

        DelightTraining first = null;

        //trainings.add(first);

        return trainings;
    }

    @Deprecated
    private List<ChatMessage> initMessage() {
        List<ChatMessage> chatMessages = new ArrayList<>();
        ChatMessage chatMessageOne = new ChatMessage("hello", "anton", false);
        ChatMessage chatMessageTwo = new ChatMessage("hello", "anton", true);
        chatMessages.add(chatMessageOne);
        chatMessages.add(chatMessageTwo);
        return chatMessages;
    }

    View.OnClickListener clickFab = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), AddEventActivity.class);
            //startActivityForResult(intent, REQUEST_ADD);
            startActivity(intent);
        }
    };

}
