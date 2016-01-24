package ru.delightfire.delight.ui.listener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import ru.delightfire.delight.ui.activity.AddEventActivity;

/**
 * Created by scaredChatsky on 24.01.2016.
 */
public class CancelClickListener implements View.OnClickListener {

    private Context context;

    public CancelClickListener(Context context){
        this.context = context;
    }

    @Override
    public void onClick(View v) {

        AddEventActivity addEventActivity = ((AddEventActivity) context);
        Intent data = new Intent();
        addEventActivity.setResult(Activity.RESULT_CANCELED, data);
        addEventActivity.finish();

    }

}
