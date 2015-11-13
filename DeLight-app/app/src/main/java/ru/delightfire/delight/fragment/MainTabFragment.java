package ru.delightfire.delight.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import java.util.concurrent.ExecutionException;

import ru.delightfire.delight.R;
import ru.delightfire.delight.adapter.ChatAdapter;
import ru.delightfire.delight.adapter.TrainingAdapter;
import ru.delightfire.delight.entity.DelightTraining;
import ru.delightfire.delight.entity.Message;
import ru.delightfire.delight.utils.DelightContext;

/**
 * Created by sergei on 12.11.2015.
 */
public class MainTabFragment extends Fragment {

    private DelightContext context = DelightContext.getInstance();
    private List<DelightTraining> trainings;
    private List<Message> messages;
    private ListView listView;
    private ListView listMessages;
    private Button btnSendMsg;
    private EditText messageTxt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.tab_host, container, false);

        btnSendMsg = (Button) view.findViewById(R.id.btnView_send_msg);
        messageTxt = (EditText) view.findViewById(R.id.inputView_msg);
        TabHost tabs = (TabHost) view.findViewById(android.R.id.tabhost);

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

        listView = (ListView) view.findViewById(R.id.events_list);
        listMessages = (ListView) view.findViewById(R.id.listView_chat_messages);

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

        return view;
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

}
