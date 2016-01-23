package ru.delightfire.delight.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import ru.delightfire.delight.R;

/**
 * Created by sergei on 22.11.2015.
 */
public class AddShowFragment extends Fragment implements View.OnClickListener {

    private TextView datePerformance;
    private Button chooseDate;
    private TimePicker time;
    private DatePicker date;
    private Dialog dialogPicker;
    private Button setDate;
    private int month, day, year, hour, minute;
    private Calendar calendar;
    private Date dateTime = null;
    private Button btnSaveShow;
    private static final String DATE_SHOW_BUNDLE_KEY = "date_show_bundle_key";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_performance, container, false);
        datePerformance = (TextView) view.findViewById(R.id.date_performance);
        chooseDate = (Button) view.findViewById(R.id.choose_date);
        chooseDate.setOnClickListener(this);
        btnSaveShow = (Button) view.findViewById(R.id.btn_save);
        btnSaveShow.setOnClickListener(saveListener);
        if(calendar == null){
            calendar = Calendar.getInstance();
        }
        if(savedInstanceState != null && savedInstanceState.containsKey(DATE_SHOW_BUNDLE_KEY)){
            dateTime = new Date();//
            dateTime.setTime(savedInstanceState.getLong(DATE_SHOW_BUNDLE_KEY));
            DateFormat df = DateFormat.getDateTimeInstance
                    (DateFormat.LONG, DateFormat.DEFAULT, new Locale("ru", "RU"));
            datePerformance.setText(df.format(dateTime));
            Log.d("On Create View: ", "date: " + df.format(dateTime));
        }
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onClick(View v) {

        dialogPicker = new Dialog(getActivity());
        dialogPicker.setContentView(R.layout.picker_dialog);
        dialogPicker.setTitle("Выбери дату и время");
        time = (TimePicker) dialogPicker.findViewById(R.id.timePicker1);
//        time.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
//        time.setCurrentMinute(calendar.get(Calendar.MINUTE));
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
                datePerformance.setText(df.format(calendar.getTime()));
                dialogPicker.dismiss();
            }
        });
        dialogPicker.show();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(dateTime != null){
            outState.putLong(DATE_SHOW_BUNDLE_KEY, dateTime.getTime());
        }
    }
    View.OnClickListener saveListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
}
