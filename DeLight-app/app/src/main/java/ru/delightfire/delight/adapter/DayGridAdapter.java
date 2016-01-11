package ru.delightfire.delight.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import ru.delightfire.delight.R;

/**
 * Created by sergei on 04.12.2015.
 */
public class DayGridAdapter extends ArrayAdapter<String> {

    Context context;
    String[] days = null;


    public DayGridAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
        days = context.getResources().getStringArray(R.array.arr_days);
    }

    @Override
    public String getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getPosition(String item) {
        return super.getPosition(item);
    }

    @Override
    public int getCount() {
        return days.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.day_of_week_grid, parent, false);
            TextView day = (TextView) convertView.findViewById(R.id.item_day);
            day.setText(days[position]);
        }
        return convertView;
    }
}
