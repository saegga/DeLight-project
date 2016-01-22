package ru.delightfire.delight.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ru.delightfire.delight.R;
import ru.delightfire.delight.entity.subject.DelightEvent;

/**
 * Created by sergei on 09.01.2016.
 */
public class EventAdapter extends BaseAdapter {

    /**
     * Collection of events
     */
    private List<DelightEvent> events;
    private LayoutInflater layoutInflater;

    public EventAdapter(Context context, List<DelightEvent> events) {
        this.events = events;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return events.size();
    }

    @Override
    public Object getItem(int position) {
        return events.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            view = layoutInflater.inflate(R.layout.element_list_row_event, parent, false);
        }

        DelightEvent event = events.get(position);

        TextView place = (TextView) view.findViewById(R.id.tv_element_list_row_event_place);
        TextView date = (TextView) view.findViewById(R.id.tv_element_list_row_event_date);
        TextView startTime = (TextView) view.findViewById(R.id.tv_element_list_row_event_start_time);
        TextView endTime = (TextView) view.findViewById(R.id.tv_element_list_row_event_end_time);

        place.setText(Integer.toString(event.getPlaceId()));
        date.setText(event.getDate());
        startTime.setText(event.getStartTime());
        endTime.setText(event.getEndTime());

        return view;
    }
}
