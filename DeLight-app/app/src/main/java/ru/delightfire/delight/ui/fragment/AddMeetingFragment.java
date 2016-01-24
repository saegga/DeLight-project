package ru.delightfire.delight.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;

import ru.delightfire.delight.R;
import ru.delightfire.delight.entity.subject.DelightEvent;
import ru.delightfire.delight.ui.activity.AddEventActivity;
import ru.delightfire.delight.ui.listener.SetDateClickListener;
import ru.delightfire.delight.ui.listener.SetTimeClickListener;

/**
 * Created by sergei on 24.11.2015.
 */
public class AddMeetingFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_meeting, container, false);

        AppCompatEditText startTime = (AppCompatEditText) rootView.findViewById(R.id.acet_fragment_add_meeting_start_time);
        AppCompatEditText endTime = (AppCompatEditText) rootView.findViewById(R.id.acet_fragment_add_meeting_end_time);

        AppCompatEditText date = (AppCompatEditText) rootView.findViewById(R.id.acet_fragment_add_meeting_date);

        Calendar calendar = Calendar.getInstance();
        Integer year = calendar.get(Calendar.YEAR);
        Integer month = calendar.get(Calendar.MONTH);
        Integer dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        startTime.setOnClickListener(new SetTimeClickListener(getActivity()));
        endTime.setOnClickListener(new SetTimeClickListener(getActivity()));
        date.setOnClickListener(new SetDateClickListener(getActivity(), year, month, dayOfMonth));

        date.setText(dayOfMonth + " " + DelightEvent.getMonthName(month + 1) + " " + year);

        startTime.setText("18:00");
        endTime.setText("21:00");

        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((AddEventActivity) getActivity()).getSupportActionBar().setTitle("Добавить встручу");
    }
}
