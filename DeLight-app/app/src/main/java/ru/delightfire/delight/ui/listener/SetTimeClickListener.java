package ru.delightfire.delight.ui.listener;

import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import ru.delightfire.delight.util.LoadingChecker;

/**
 * Created by scaredChatsky on 24.01.2016.
 */
public class SetTimeClickListener implements View.OnClickListener {

    private Context context;

    public SetTimeClickListener(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        final EditText editText = (EditText) v;
        int hour = Integer.parseInt(editText.getText().toString().substring(0, 2));
        int minute = Integer.parseInt(editText.getText().toString().substring(3, 5));
        TimePickerDialog pickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                StringBuilder stringBuilder = new StringBuilder();

                stringBuilder.append(String.valueOf(hourOfDay).length() == 1 ? new StringBuilder("0").append(hourOfDay) : hourOfDay);
                stringBuilder.append(":");
                stringBuilder.append(String.valueOf(minute).length() == 1 ? new StringBuilder("0").append(minute) : minute);

                editText.setText(stringBuilder);
            }
        }, hour, minute, true);
        pickerDialog.setTitle("");
        pickerDialog.show();
    }

}
