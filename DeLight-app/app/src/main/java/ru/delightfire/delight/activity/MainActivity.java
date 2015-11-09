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
import java.util.concurrent.ExecutionException;

import ru.delightfire.delight.R;
import ru.delightfire.delight.adapter.ChatAdapter;
import ru.delightfire.delight.adapter.TrainingAdapter;
import ru.delightfire.delight.entity.DelightTraining;
import ru.delightfire.delight.entity.Message;
import ru.delightfire.delight.utils.DelightContext;

/**
 * Created by scaredChatsky on 23.10.2015.
 */
public class MainActivity extends Activity{

    private DelightContext context = DelightContext.getInstance();

    private List<DelightTraining> trainings;
    private List<Message> messages;
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

        TrainingAdapter adapter = new TrainingAdapter(this, initEvents());
        listView.setAdapter(adapter);


        ChatAdapter chatAdapter = new ChatAdapter(this, initMessage());
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

        DelightTraining first = null;
        try {
            first = context.getTraining(1);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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
