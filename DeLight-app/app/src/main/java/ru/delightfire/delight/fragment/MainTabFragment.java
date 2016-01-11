package ru.delightfire.delight.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.delightfire.delight.R;
import ru.delightfire.delight.activity.AddEventActivity;
import ru.delightfire.delight.adapter.ChatAdapter;
import ru.delightfire.delight.adapter.EventAdapter;
import ru.delightfire.delight.adapter.TrainingAdapter;
import ru.delightfire.delight.entity.ChatMessage;
import ru.delightfire.delight.entity.DelightEvent;
import ru.delightfire.delight.entity.DelightMeeting;
import ru.delightfire.delight.entity.DelightShow;
import ru.delightfire.delight.entity.DelightTraining;
import ru.delightfire.delight.utils.DelightMeetDeserializer;
import ru.delightfire.delight.utils.DelightShowDeserializer;
import ru.delightfire.delight.utils.DelightTrainingDeserializer;


/**
 * Created by sergei on 12.11.2015.
 */
public class MainTabFragment extends Fragment{


    private static final String TAG = "MainFragment";
    private ListView listViewEvent;
    private EventAdapter eventAdapter;
    public static final String FETCH_ALL_EVENTS = "http://delightfireapp.16mb.com/app/androidQueries/get/get_all_events.php";

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
    }

    @Deprecated
    private void sendMessage(String msg) {

    }

    //TODO: Инициализация всех событий

    private void initEvents(final IonCallback callback) {

        // получаем все события
       Ion.with(this).load("POST", FETCH_ALL_EVENTS)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if (e != null) {
                            Log.d(TAG, e.getMessage());
                            return;
                        }
                        Gson gson;
                        Type trainingType = new TypeToken<List<DelightTraining>>() {}.getType(); // тип для списка объектов
                        Type showType = new TypeToken<List<DelightShow>>() {}.getType();
                        Type meetType = new TypeToken<List<DelightMeeting>>() {}.getType();
                        List<DelightEvent> events = new ArrayList<>();
                        List<DelightTraining> trainings;
                        List<DelightShow> shows;
                        List<DelightMeeting> meets;

                        gson = new GsonBuilder().
                                registerTypeAdapter(trainingType, new DelightTrainingDeserializer())
                                .create();
                        trainings = gson.fromJson(result.toString(), trainingType); // массив тренировок

                        gson = new GsonBuilder()
                                .registerTypeAdapter(showType, new DelightShowDeserializer())
                                .create();
                        shows = gson.fromJson(result.toString(), showType);


                        gson = new GsonBuilder()
                                .registerTypeAdapter(meetType, new DelightMeetDeserializer())
                                .create();
                        meets = gson.fromJson(result.toString(), meetType);

                        events.addAll(trainings);
                        events.addAll(shows);
                        events.addAll(meets);
                        callback.onResult(events);
                        Log.d(TAG, Arrays.asList(events).toString());
                        Log.d(TAG, result.toString());

                    }
                });
        //return null;
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

    public interface IonCallback{
        void onResult(List<DelightEvent> events);
    }

}
