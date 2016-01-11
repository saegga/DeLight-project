package ru.delightfire.delight.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ru.delightfire.delight.R;
import ru.delightfire.delight.entity.DelightEvent;
import ru.delightfire.delight.entity.DelightMeeting;
import ru.delightfire.delight.entity.DelightShow;
import ru.delightfire.delight.entity.DelightTraining;

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
        if(view == null){
            view = layoutInflater.inflate(R.layout.element_list_row_event, parent, false);
        }
        DelightEvent event = events.get(position);

        TextView titleEvent = (TextView) view.findViewById(R.id.title_event);
        TextView descEvent = (TextView) view.findViewById(R.id.description_event);
        TextView time = (TextView) view.findViewById(R.id.time_event);
        TextView dateEvent = (TextView) view.findViewById(R.id.day_event);
        titleEvent.setText(event.getName());
        descEvent.setText(event.getAgenda());

        if(event instanceof DelightMeeting || event instanceof DelightShow){
            dateEvent.setText(event.getDateEvent());
        }else{
            time.setText(event.getTimeEvent());
            dateEvent.setText(event.getDateEvent());
        }

        return view;
    }
}
