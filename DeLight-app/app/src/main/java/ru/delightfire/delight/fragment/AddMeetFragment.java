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

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import ru.delightfire.delight.R;

/**
 * Created by sergei on 24.11.2015.
 */
public class AddMeetFragment extends Fragment implements View.OnClickListener {

    private static final String DATE_MEET_BUNDLE_KEY = "date_meet_bundle_key";
    private EditText editAgenda;
    private Button btnSetDate;
    private Button setDate;
    private TimePicker time;
    private DatePicker date;
    private Dialog dialogPicker;
    private int month, day, year, hour, minute;
    private Calendar calendar;
    private Date dateTime;
    private TextView dateMeetView;
    private Button btnSaveMeet;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_meet, container, false);
        editAgenda = (EditText) view.findViewById(R.id.agenda_meet_input);
        btnSetDate = (Button) view.findViewById(R.id.btn_meet_set_date);
        dateMeetView = (TextView) view.findViewById(R.id.meet_data);
        btnSaveMeet = (Button) view.findViewById(R.id.btn_save);
        btnSaveMeet.setOnClickListener(saveListener);
        btnSetDate.setOnClickListener(this);
        if (calendar == null) {
            calendar = Calendar.getInstance();
        }
        if (savedInstanceState != null && savedInstanceState.containsKey(DATE_MEET_BUNDLE_KEY)) {
            dateTime = new Date();//
            dateTime.setTime(savedInstanceState.getLong(DATE_MEET_BUNDLE_KEY));
            DateFormat df = DateFormat.getDateTimeInstance
                    (DateFormat.LONG, DateFormat.DEFAULT, new Locale("ru", "RU"));
            dateMeetView.setText(df.format(dateTime));
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        dialogPicker = new Dialog(getActivity());
        dialogPicker.setContentView(R.layout.picker_dialog);
        dialogPicker.setTitle("Выбери дату и время");
        time = (TimePicker) dialogPicker.findViewById(R.id.timePicker1);
        time.setIs24HourView(true);
        date = (DatePicker) dialogPicker.findViewById(R.id.datePicker);
        setDate = (Button) dialogPicker.findViewById(R.id.btnSet);
        setDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dateTime = new Date();// инициализация даты
                month = date.getMonth();
                year = date.getYear();
                day = date.getDayOfMonth();
                hour = time.getCurrentHour();
                minute = time.getCurrentMinute();
                calendar.set(Calendar.DAY_OF_MONTH, day);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MINUTE, minute);
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                dateTime = calendar.getTime();
                DateFormat df = DateFormat.getDateTimeInstance
                        (DateFormat.LONG, DateFormat.DEFAULT, new Locale("ru", "RU"));
                dateMeetView.setText(df.format(calendar.getTime()));
                dialogPicker.dismiss();
            }
        });
        dialogPicker.show();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (dateTime != null) {
            outState.putLong(DATE_MEET_BUNDLE_KEY, dateTime.getTime());
        }
    }

    View.OnClickListener saveListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // todo сделать сохранение
        }
    };
}
