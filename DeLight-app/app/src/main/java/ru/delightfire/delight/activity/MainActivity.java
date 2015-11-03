package ru.delightfire.delight.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.List;

import ru.delightfire.delight.R;
import ru.delightfire.delight.adapter.DelightChatAdapter;
import ru.delightfire.delight.adapter.DelightTrainingAdapter;
import ru.delightfire.delight.entity.DelightTraining;
import ru.delightfire.delight.entity.Message;

/**
 * Created by scaredChatsky on 23.10.2015.
 */
public class MainActivity extends Activity{

    List<DelightTraining> trainings;
    List<Message> messages;
    private ListView listView;
    private ListView listMessages;
    private Button btnSendMsg;
    private EditText messageTxt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSendMsg = (Button) findViewById(R.id.btnView_send_msg);
        messageTxt = (EditText) findViewById(R.id.inputView_msg);
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
        listMessages = (ListView) findViewById(R.id.listView_chat_messages);

        DelightTrainingAdapter adapter = new DelightTrainingAdapter(this, initEvents());
        listView.setAdapter(adapter);


        DelightChatAdapter chatAdapter = new DelightChatAdapter(this, initMessage());
        listMessages.setAdapter(chatAdapter);

        btnSendMsg.setOnClickListener(new View.OnClickListener() {
            String msg = messageTxt.getText().toString();
            @Override
            public void onClick(View v) {
                sendMessage(msg);
            }
        });
    }

    private void sendMessage(String msg) {

    }

    private List<DelightTraining> initEvents(){
        trainings = new ArrayList<>();

        DelightTraining first = new DelightTraining("admin", "first");

        trainings.add(first);

        return trainings;
    }
    private List<Message> initMessage(){
        messages = new ArrayList<>();
        Message messageOne = new Message("hello", "anton", false);
        Message messageTwo = new Message("hello", "anton", true);
        messages.add(messageOne);
        messages.add(messageTwo);
        return messages;
    }

}
