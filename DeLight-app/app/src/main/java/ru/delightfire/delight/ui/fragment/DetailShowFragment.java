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

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;

import ru.delightfire.delight.R;
import ru.delightfire.delight.entity.subject.DelightShow;
import ru.delightfire.delight.ui.activity.DetailEventActivity;
import ru.delightfire.delight.util.DelightEventDeserializer;
import ru.delightfire.delight.util.DelightShowSerializer;

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
    private int eventId;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventId = getArguments().getInt(DetailEventActivity.BUNDLE_EVENT_ID, -1);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void loadDetailShow(){
        Ion.with(getActivity())
                .load("POST", "http://delightfire-sunteam.rhcloud.com/app/androidQueries/get/get_show")
                .setBodyParameter("event_id", String.valueOf(eventId))
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if (e != null) {
                            new MaterialDialog.Builder(getActivity())
                                    .title(R.string.error)
                                    .content(R.string.check_connection)
                                    .backgroundColorRes(R.color.mainBackground)
                                    .positiveColorRes(R.color.white)
                                    .positiveText(R.string.ok)
                                    .show();
                            return;
                        }else if(result.get("success").getAsInt() == 1){
                            
                        }
                    }
                });
    }
}
