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
 * Created by scaredChatsky on 25.10.2015.
 * Adapter for DelightTraining entity
 */
public class TrainingAdapter extends BaseAdapter{

    /**
     * Collection of trainings
     */
    private List<DelightTraining> trainings;
    /**
     *
     */
    private LayoutInflater layoutInflater;

    public TrainingAdapter(Context context, List<DelightTraining> events) {
        this.trainings = events;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
            view = layoutInflater.inflate(R.layout.element_list_row_event, parent, false);
        }

        DelightTraining delightTraining= getDelightTraining(position);

        TextView textView = (TextView) view.findViewById(R.id.artist);
        textView.setText(delightTraining.getName());

        return view;
    }

    private DelightTraining getDelightTraining(int position){
        return (DelightTraining) getItem(position);
    }
}
