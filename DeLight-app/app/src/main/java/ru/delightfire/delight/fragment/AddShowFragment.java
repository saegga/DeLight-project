package ru.delightfire.delight.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import ru.delightfire.delight.R;

/**
 * Created by sergei on 22.11.2015.
 */
public class AddShowFragment extends Fragment implements View.OnClickListener {

    private TextView namePerformance;
    private TextView descPerformance;
    private Button chooseDate;
    private TimePicker time;
    private DatePicker date;
    private Dialog dialogPicker;
    private Button setDate;
    private int month, day, year, hour, minute;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_show, container, false);
        namePerformance = (TextView) view.findViewById(R.id.name_performance);
        descPerformance = (TextView) view.findViewById(R.id.description_performance);
        chooseDate = (Button) view.findViewById(R.id.choose_date);
        chooseDate.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        dialogPicker = new Dialog(getActivity());
        dialogPicker.setContentView(R.layout.picker_dialog);
        dialogPicker.setTitle("Выбери дату и время");
        time = (TimePicker) dialogPicker.findViewById(R.id.timePicker1);
        date = (DatePicker) dialogPicker.findViewById(R.id.datePicker);
        setDate = (Button) dialogPicker.findViewById(R.id.btnSet);
        setDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                month = date.getMonth();
                year = date.getYear();
                day = date.getDayOfMonth();
                hour = time.getCurrentHour();
                minute = time.getCurrentMinute();
                dialogPicker.dismiss();
            }
        });
        dialogPicker.show();
    }
}
