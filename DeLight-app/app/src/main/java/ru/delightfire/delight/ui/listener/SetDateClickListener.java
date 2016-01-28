package ru.delightfire.delight.ui.listener;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import ru.delightfire.delight.entity.subject.DelightEvent;

/**
 * Created by dev on 24.01.2016.
 */
public class SetDateClickListener implements View.OnClickListener {

    private Context context;

    private Integer mYear;
    private Integer mMonth;
    private Integer mDayOfMonth;

    public SetDateClickListener(Context context, Integer year, Integer month, Integer dayOfMonth) {
        this.context = context;
        this.mYear = year;
        this.mMonth = month;
        this.mDayOfMonth = dayOfMonth;
    }

    @Override
    public void onClick(View v) {
        final EditText editText = (EditText) v;

        DatePickerDialog pickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                editText.setText(dayOfMonth + " " + DelightEvent.getMonthName(monthOfYear + 1) + " " + year);
                mYear = year;
                mMonth = monthOfYear;
                mDayOfMonth = dayOfMonth;
            }
        }, mYear, mMonth, mDayOfMonth);

        pickerDialog.show();
    }

    public Integer getmMonth() {
        return mMonth;
    }

    public Integer getmDayOfMonth() {
        return mDayOfMonth;
    }
}
