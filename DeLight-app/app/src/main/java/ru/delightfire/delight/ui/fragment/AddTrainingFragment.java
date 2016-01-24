package ru.delightfire.delight.ui.fragment;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TimePicker;

import ru.delightfire.delight.R;
import ru.delightfire.delight.ui.activity.AddEventActivity;

/**
 * Created by sergei on 27.11.2015.
 */
public class AddTrainingFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_training, container, false);

        AppCompatEditText startTime = (AppCompatEditText) rootView.findViewById(R.id.acet_fragment_add_training_start_time);
        AppCompatEditText endTime = (AppCompatEditText) rootView.findViewById(R.id.acet_fragment_add_training_end_time);

        startTime.setOnClickListener(new SetTimeClickListener());
        endTime.setOnClickListener(new SetTimeClickListener());

        startTime.setText("18:00");
        endTime.setText("21:00");

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
            final EditText editText = (EditText) v;
            int hour = Integer.parseInt(editText.getText().toString().substring(0, 2));
            int minute = Integer.parseInt(editText.getText().toString().substring(3, 5));
            TimePickerDialog pickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    StringBuilder stringBuilder = new StringBuilder();

                    stringBuilder.append(String.valueOf(hourOfDay).length() == 1 ? new StringBuilder("0").append(hourOfDay) : hourOfDay);
                    stringBuilder.append(":");
                    stringBuilder.append(String.valueOf(minute).length() == 1 ? new StringBuilder("0").append(minute) : minute);

                    editText.setText(stringBuilder);
                }
            }, hour, minute, true);
            pickerDialog.setTitle("");
            pickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "ОК", pickerDialog);
            pickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "ОТМЕНА", pickerDialog);
            pickerDialog.show();
        }
    }
}
