package ru.delightfire.delight.activity;

import android.app.FragmentManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import ru.delightfire.delight.fragment.MyProfileFragment;
import ru.delightfire.delight.utils.DelightContext;

/**
 * Created by scaredChatsky on 23.10.2015.
 */
public class MainActivity extends AppCompatActivity{

    private DelightContext context = DelightContext.getInstance();

    private List<DelightTraining> trainings;
    private List<Message> messages;
    private ListView listView;
    private ListView listMessages;
    private Button btnSendMsg;
    private EditText messageTxt;
    private DrawerLayout drawerLayout;
    private ListView drawerListView;
    private String[] listItems;

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

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerListView = (ListView) findViewById(R.id.list_drawer);
        listItems = getResources().getStringArray(R.array.drawer_items);
        drawerListView.setAdapter(new ArrayAdapter<>(this, R.layout.drawer_layout_item, listItems));
        drawerListView.setOnItemClickListener(new DrawerItemClick());



    }

    class GetTraining extends AsyncTask<Integer, Void, DelightTraining> {

        @Override
        protected DelightTraining doInBackground(Integer... trainingId) {
            DelightTraining training = null;

            training = context.getTraining(trainingId[0]);

            return training;
        }
    }

    @Deprecated
    private void sendMessage(String msg) {

    }

    //TODO: Инициализация всех событий
    private List<DelightTraining> initEvents(){
        trainings = new ArrayList<>();

        DelightTraining first = null;
        try {
            first = new GetTraining().execute(1).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        trainings.add(first);

        return trainings;
    }

    @Deprecated
    private List<Message> initMessage(){
        messages = new ArrayList<>();
        Message messageOne = new Message("hello", "anton", false);
        Message messageTwo = new Message("hello", "anton", true);
        messages.add(messageOne);
        messages.add(messageTwo);
        return messages;
    }
    private class DrawerItemClick implements ListView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Fragment fragment = null;
            switch (position){
                case 0 : fragment = new MyProfileFragment();
                case 1 : break;
                case 2 : break;
                default: break;
            }
            FragmentManager fragmentManager = getFragmentManager();
//            fragmentManager.beginTransaction()
//                  //  .replace(R.id.content_frame, fragment)
//                    .commit();
            //todo переделать main под фрагменты с использованием frameLayout

        }
    }


}
