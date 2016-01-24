package ru.delightfire.delight.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.delightfire.delight.R;
import ru.delightfire.delight.ui.activity.AddEventActivity;
import ru.delightfire.delight.ui.listener.SetTimeClickListener;

/**
 * Created by sergei on 22.11.2015.
 */
public class AddShowFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_show, container, false);

        AppCompatEditText startTime = (AppCompatEditText) rootView.findViewById(R.id.acet_fragment_add_show_start_time);
        AppCompatEditText endTime = (AppCompatEditText) rootView.findViewById(R.id.acet_fragment_add_show_end_time);

        startTime.setOnClickListener(new SetTimeClickListener(getActivity()));
        endTime.setOnClickListener(new SetTimeClickListener(getActivity()));

        startTime.setText("18:00");
        endTime.setText("21:00");

        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((AddEventActivity) getActivity()).getSupportActionBar().setTitle("Добавить выступление");
    }

}
