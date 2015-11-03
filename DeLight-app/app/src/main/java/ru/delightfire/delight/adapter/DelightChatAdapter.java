package ru.delightfire.delight.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.delightfire.delight.R;
import ru.delightfire.delight.entity.Message;

/**
 * Created by sergei on 02.11.2015.
 */
public class DelightChatAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private Context context;
    private List<Message> listMessages;

    public DelightChatAdapter(Context context, List<Message> listMessages) {
        this.context = context;
        this.listMessages = listMessages;
    }

    @Override
    public int getCount() {
        return listMessages.size();
    }

    @Override
    public Object getItem(int position) {
        return listMessages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        layoutInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Message messageItem = listMessages.get(position);

       if(convertView == null){
           if(messageItem.isYourMsg()){
               convertView = layoutInflater.inflate(R.layout.right_chat_dialog,
                       null);
           }else{
               convertView = layoutInflater.inflate(R.layout.left_chat_dialog,
                       null);
           }
       }
        TextView textMsg = (TextView) convertView.findViewById(R.id.textMsg);
        TextView nameMsg = (TextView) convertView.findViewById(R.id.nameMsg);
        textMsg.setText(messageItem.getMessage());
        nameMsg.setText(messageItem.getName());
        return convertView;
    }

}
