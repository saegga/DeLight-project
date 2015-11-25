package ru.delightfire.delight.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.delightfire.delight.R;

/**
 * Created by sergei on 22.11.2015.
 */
public class AddPerformanceFragment extends Fragment {

    TextView namePerformance;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_performance, container, false);
        namePerformance = (TextView) view.findViewById(R.id.name_performance);
        return view;
    }
}
