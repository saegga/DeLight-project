package ru.delightfire.delight.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import ru.delightfire.delight.R;

/**
 * Created by sergei on 27.11.2015.
 */
public class AddTrainingFragment extends Fragment implements View.OnClickListener {

    private TextView chooseUser;
    private TextView dayOfWeek;
    private TextView time, timeDataView;
    private Spinner usersSpinner;
    private Spinner daysSpinner;
    private TimePicker timePicker;
    private Button btnSetTime;
    private Dialog dialog;
    private Button btnOk;
    private String timeData;
    private Calendar calendar;

    private static final String TIME_BUNDLE_KEY = "time_bundle_key";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_training, container, false);
        chooseUser = (TextView) view.findViewById(R.id.text_view_choose_users);
        dayOfWeek = (TextView) view.findViewById(R.id.text_view_day);
        time = (TextView) view.findViewById(R.id.text_view_time);
        timeDataView = (TextView) view.findViewById(R.id.time_data);
        usersSpinner = (Spinner) view.findViewById(R.id.list_users);
        SpinnerAdapter adapter = new ArrayAdapter<>(getActivity(),
                R.layout.spinner_user_row, R.id.user_name ,getArrays());
        usersSpinner.setAdapter(adapter);
        // todo сделать spinner
        daysSpinner = (Spinner) view.findViewById(R.id.day_of_week);
        btnSetTime = (Button) view.findViewById(R.id.btn_set_time);
        btnSetTime.setOnClickListener(this);
        if(calendar == null){
          calendar = Calendar.getInstance();
        }
        if(savedInstanceState != null && savedInstanceState.containsKey(TIME_BUNDLE_KEY)){
            timeDataView.setText(savedInstanceState.getString(TIME_BUNDLE_KEY));
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.time_picker);
        dialog.setTitle("Выбери время");
        timePicker = (TimePicker) dialog.findViewById(R.id.set_time);
        timePicker.setIs24HourView(true);
        btnOk = (Button) dialog.findViewById(R.id.btn_ok);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
                calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());
                DateFormat df = DateFormat.getTimeInstance(DateFormat.SHORT);
                timeData = df.format(calendar.getTime());
                Log.d("TIme: ", timeData);
                timeDataView.setText(timeData);
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    private ArrayList<String> getArrays(){
        ArrayList<String> list = new ArrayList<>();
        list.add("first");
        list.add("first");
        list.add("first");
        list.add("first");
        return list;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TIME_BUNDLE_KEY, timeData);
    }
}
