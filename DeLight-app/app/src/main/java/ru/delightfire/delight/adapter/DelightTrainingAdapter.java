package ru.delightfire.delight.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ru.delightfire.delight.R;
import ru.delightfire.delight.entity.DelightTraining;

/**
 * Created by dev on 25.10.2015.
 */
public class DelightTrainingAdapter extends BaseAdapter{

    private List<DelightTraining> trainings;
    private LayoutInflater layoutInflater;

    public DelightTrainingAdapter(Context context, List<DelightTraining> events) {
        this.trainings = events;
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return trainings.size();
    }

    @Override
    public Object getItem(int position) {
        return trainings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null){
            view = layoutInflater.inflate(R.layout.list_row_event, parent, false);
        }

        DelightTraining delightTraining= getDelightTraining(position);

        TextView textView = (TextView) view.findViewById(R.id.title);
        textView.setText(delightTraining.getName());

        return view;
    }

    private DelightTraining getDelightTraining(int position){
        return (DelightTraining) getItem(position);
    }
}
