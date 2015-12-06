package ru.delightfire.delight.fragment;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import ru.delightfire.delight.R;
import ru.delightfire.delight.adapter.DayGridAdapter;

/**
 * Created by sergei on 27.11.2015.
 */
public class AddTrainingFragment extends Fragment implements View.OnClickListener {

    private static final String TIME_BUNDLE_KEY = "time_bundle_key";
    private static final String DAY_BUNDLE_KEY = "day_bundle_key";

    private TextView dayOfWeek;
    private TextView timeDataView;
    private Spinner usersSpinner;
    private TimePicker timePicker;
    private Button btnSetTime;
    private Dialog dialog;
    private Button btnOk;
    private Calendar calendar;
    private GridView grid;
    private String timeData;
    private String[] arrDays;
    private String day;
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_training, container, false);

        timeDataView = (TextView) view.findViewById(R.id.time_data);
        usersSpinner = (Spinner) view.findViewById(R.id.list_users);
        SpinnerAdapter adapter = new ArrayAdapter<>(getActivity(),
                R.layout.spinner_user_row, R.id.user_name ,getArrays());
        usersSpinner.setAdapter(adapter);
        dayOfWeek = (TextView) view.findViewById(R.id.day_of_week_data);
        btnSetTime = (Button) view.findViewById(R.id.btn_set_time);
        btnSetTime.setOnClickListener(this);
        if(calendar == null){
          calendar = Calendar.getInstance();
        }
        if(savedInstanceState != null){
            if(savedInstanceState.containsKey(TIME_BUNDLE_KEY)){
                timeData = savedInstanceState.getString(TIME_BUNDLE_KEY);
                timeDataView.setText(timeData);
            }

            if(savedInstanceState.containsKey(DAY_BUNDLE_KEY)){
                day = savedInstanceState.getString(DAY_BUNDLE_KEY);
                dayOfWeek.setText(day);
            }
        }
        grid = (GridView) view.findViewById(R.id.test_grid);
        DayGridAdapter dayGridAdapter = new DayGridAdapter(getActivity(), R.layout.day_of_week_grid);
        grid.setAdapter(dayGridAdapter);
        grid.setOnItemClickListener(itemGridListener);
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
        if(timeData!= null) {
            outState.putString(TIME_BUNDLE_KEY, timeData);
        }
        if(day != null){
            outState.putString(DAY_BUNDLE_KEY, day);
        }
    }
    public AbsListView.OnItemClickListener itemGridListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            day = arrDays[position];
            dayOfWeek.setText(day);
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrDays = getResources().getStringArray(R.array.arr_days_full);
    }

}
