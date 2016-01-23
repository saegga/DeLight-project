package ru.delightfire.delight.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import ru.delightfire.delight.R;
import ru.delightfire.delight.ui.activity.AddEventActivity;

/**
 * Created by sergei on 27.11.2015.
 */
public class AddTrainingFragment extends Fragment {

    private TimePicker picker;
    private int hour;
    private int minute;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_training, container, false);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        String currentTime = format.format(calendar.getTime());
        hour = Integer.parseInt(currentTime.substring(0, 2));
        minute = Integer.parseInt(currentTime.substring(3, 5));

        AppCompatEditText startTime = (AppCompatEditText) rootView.findViewById(R.id.acet_fragment_add_training_start_time);
        AppCompatEditText endTime = (AppCompatEditText) rootView.findViewById(R.id.acet_fragment_add_training_end_time);
        startTime.setOnClickListener(new SetTimeClickListener());
        endTime.setOnClickListener(new SetTimeClickListener());
        startTime.setText(currentTime);

        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((AddEventActivity) getActivity()).getSupportActionBar().setTitle("Добавить тренировку");
    }

    private class SetTimeClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Dialog dialogPicker = new Dialog(getActivity());
            dialogPicker.setContentView(R.layout.element_time_picker);
            picker = (TimePicker) dialogPicker.findViewById(R.id.tp_element_time_picker);
            picker.setIs24HourView(true);
            picker.setCurrentHour(hour);
            picker.setCurrentMinute(minute);
            dialogPicker.show();
        }
    }
}
