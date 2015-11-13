package ru.delightfire.delight.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import ru.delightfire.delight.R;

/**
 * Created by sergei on 12.11.2015.
 */
public class MyProfileFragment extends Fragment {

    TextView text;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_profile, container, false);
        text = (TextView) view.findViewById(R.id.test);
        text.setText("Профиль");
        return view;
    }
}
