package ru.delightfire.delight.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.Calendar;

import ru.delightfire.delight.R;
import ru.delightfire.delight.entity.subject.DelightEvent;
import ru.delightfire.delight.entity.subject.DelightShow;
import ru.delightfire.delight.ui.activity.AddEventActivity;
import ru.delightfire.delight.ui.activity.MainActivity;
import ru.delightfire.delight.ui.listener.CancelClickListener;
import ru.delightfire.delight.ui.listener.OkClickListener;
import ru.delightfire.delight.ui.listener.SetDateClickListener;
import ru.delightfire.delight.ui.listener.SetTimeClickListener;
import ru.delightfire.delight.util.DelightShowSerializer;
import ru.delightfire.delight.util.LoadingChecker;

/**
 * Created by sergei on 22.11.2015.
 */
public class AddShowFragment extends Fragment {

    private LoadingChecker checker = new LoadingChecker(5);
    private SetDateClickListener dateClickListener;
    private Spinner spinnerPlace;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AddEventActivity) getActivity()).getSupportActionBar().setTitle("Добавить выступление");
        FragmentManager manager = getActivity().getSupportFragmentManager();
        Fragment fragment = new LoadingFragment();
        manager.beginTransaction()
                .add(R.id.fl_activity_add_event_content, fragment, "loading")
                .show(fragment)
                .commit();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_show, container, false);

        final AppCompatEditText startTime = (AppCompatEditText) rootView.findViewById(R.id.acet_fragment_add_show_start_time);
        AppCompatEditText endTime = (AppCompatEditText) rootView.findViewById(R.id.acet_fragment_add_show_end_time);
        final AppCompatEditText name = (AppCompatEditText) rootView.findViewById(R.id.acet_fragment_add_show_name);
        AppCompatEditText date = (AppCompatEditText) rootView.findViewById(R.id.acet_fragment_add_show_date);
        spinnerPlace = (Spinner) rootView.findViewById(R.id.sp_fragment_add_show_place);

        Calendar calendar = Calendar.getInstance();
        Integer year = calendar.get(Calendar.YEAR);
        Integer month = calendar.get(Calendar.MONTH);
        Integer dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        startTime.setOnClickListener(new SetTimeClickListener(getActivity()));
        endTime.setOnClickListener(new SetTimeClickListener(getActivity()));
        dateClickListener = new SetDateClickListener(getActivity(), year, month, dayOfMonth);
        date.setOnClickListener(dateClickListener);

        date.setText(dayOfMonth + " " + DelightEvent.getMonthName(month + 1) + " " + year);

        AppCompatButton cancelButton = (AppCompatButton) rootView.findViewById(R.id.acb_fragment_add_show_negative);
        AppCompatButton okButton = (AppCompatButton) rootView.findViewById(R.id.acb_fragment_add_show_positive);

        cancelButton.setOnClickListener(new CancelClickListener(getActivity()));

        startTime.setText("18:00");
        endTime.setText("21:00");

        final AppCompatEditText startTimeTo = startTime;
        final AppCompatEditText endTimeTo = endTime;

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MaterialDialog materialDialog = new MaterialDialog.Builder(getActivity())
                        .title(R.string.loading)
                        .progressIndeterminateStyle(true)
                        .backgroundColorRes(R.color.mainBackground)
                        .widgetColorRes(R.color.white)
                        .progress(true, 0)
                        .show();
                String strName = name.getText().toString();
                int monthTo = dateClickListener.getmMonth() + 1;
                int dayTo = dateClickListener.getmDayOfMonth();
                int place = spinnerPlace.getSelectedItemPosition();
                DelightShow show = new DelightShow(place, monthTo, dayTo, getTime(startTimeTo),
                        getTime(endTimeTo), strName);
                Gson gson = new GsonBuilder()
                        .registerTypeAdapter(DelightShow.class, new DelightShowSerializer())
                        .create();
                Ion.with(getActivity())
                        .load("POST", "http://delightfire-sunteam.rhcloud.com/app/androidQueries/create/create_show")
                        .setBodyParameter("json", gson.toJson(show))
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {
                                materialDialog.dismiss();
                                if(e != null){
                                    return;
                                }
                                if(result != null){
                                    if (result.get("success").getAsInt() == 1) {
                                        new MaterialDialog.Builder(getActivity())
                                                .title(R.string.success)
                                                .content(R.string.success_add_show)
                                                .positiveText(R.string.ok)
                                                .backgroundColorRes(R.color.mainBackground)
                                                .positiveColorRes(R.color.white)
                                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                                    @Override
                                                    public void onClick(MaterialDialog dialog, DialogAction which) {
                                                        dialog.dismiss();
                                                        Intent data = new Intent();
                                                        data.putExtra(MainActivity.EXTRA_DATA_POSITION, 2);
                                                        getActivity().setResult(Activity.RESULT_OK, data);
                                                        getActivity().finish();
                                                }
                                                })
                                                .show();
                                    }else {
                                        new MaterialDialog.Builder(getActivity())
                                                .title(R.string.error)
                                                .positiveText(R.string.ok)
                                                .backgroundColorRes(R.color.mainBackground)
                                                .show();
                                    }
                                }else {
                                    new MaterialDialog.Builder(getActivity())
                                            .title(R.string.error)
                                            .positiveText(R.string.ok)
                                            .backgroundColorRes(R.color.mainBackground)
                                            .show();
                                }
                            }
                        });
            }
        });
        initDate();
        return rootView;
    }

    private void initDate() {
        Ion.with(getActivity())
                .load("POST", "http://delightfire-sunteam.rhcloud.com/app/androidQueries/get/get_all_places")
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        JsonArray array = result.get("places").getAsJsonArray();
                        String placesArray[] = new String[array.size()];
                        for (int i = 0; i < placesArray.length; i++) {
                            placesArray[i] = array.get(i).getAsJsonObject().get("name").getAsString();
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                                R.layout.spinner_item, placesArray);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerPlace.setAdapter(adapter);
                        initView();
                    }
                });
    }

    private void initView() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        Fragment fragment = manager.findFragmentByTag("loading");
        manager.beginTransaction()
                .hide(fragment)
                .remove(fragment)
                .commit();
    }

    public String getTime(EditText editText) {
        int hour = Integer.parseInt(editText.getText().toString().substring(0, 2));
        int minute = Integer.parseInt(editText.getText().toString().substring(3, 5));

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.valueOf(hour).length() == 1 ? new StringBuilder("0").append(hour) : hour);
        stringBuilder.append(":");
        stringBuilder.append(String.valueOf(minute).length() == 1 ? new StringBuilder("0").append(minute) : minute);

        return stringBuilder.toString();
    }
}
