package ru.delightfire.delight.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;

import ru.delightfire.delight.R;

/**
 * Created by sergei on 27.11.2015.
 */
public class AddTrainingFragment extends Fragment implements View.OnClickListener {

    private TextView chooseUser;
    private TextView dayOfWeek;
    private TextView time;
    private Spinner usersSpinner;
    private Spinner daysSpinner;
    private TimePicker timePicker;
    private Button btnSetTime;
    private Dialog dialog;
    private Button btnOk;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_training, container, false);
        chooseUser = (TextView) view.findViewById(R.id.text_view_choose_users);
        dayOfWeek = (TextView) view.findViewById(R.id.text_view_day);
        time = (TextView) view.findViewById(R.id.text_view_time);
        usersSpinner = (Spinner) view.findViewById(R.id.list_users);
        SpinnerAdapter adapter = new ArrayAdapter<>(getActivity(),
                R.layout.spinner_user_row, R.id.user_name ,getArrays());
        usersSpinner.setAdapter(adapter);
        // todo сделать spinner
        daysSpinner = (Spinner) view.findViewById(R.id.day_of_week);
        btnSetTime = (Button) view.findViewById(R.id.btn_set_time);
        btnSetTime.setOnClickListener(this);

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

}
