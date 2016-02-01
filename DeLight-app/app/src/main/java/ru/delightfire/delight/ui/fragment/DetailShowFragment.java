package ru.delightfire.delight.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;

import ru.delightfire.delight.R;
import ru.delightfire.delight.ui.activity.DetailEventActivity;

/**
 * Created by sergei on 30.01.2016.
 */
public class DetailShowFragment extends Fragment {


    private TextView nameShow;
    private TextView dateShow;
    private TextView descriptionShow;
    private TextView placeShow;
    private TextView startTimeShow, endTimeShow;
    private ActionBar toolbar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_event_show, container, false);

         toolbar = ((DetailEventActivity) getActivity()).getSupportActionBar();
        if (toolbar != null) {
            toolbar.setTitle("Выступление");
        }
        nameShow = (TextView) view.findViewById(R.id.name_show);
        dateShow = (TextView) view.findViewById(R.id.date_show);
        descriptionShow = (TextView) view.findViewById(R.id.description_show);
        startTimeShow = (TextView) view.findViewById(R.id.start_time_show);
        endTimeShow = (TextView) view.findViewById(R.id.end_time_show);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu_profile_toolbar, menu);
        menu.getItem(0).setIcon(new IconicsDrawable(getActivity())
                .icon(FontAwesome.Icon.faw_pencil_square_o)
                .colorRes(R.color.white)
                .sizeDp(20));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
