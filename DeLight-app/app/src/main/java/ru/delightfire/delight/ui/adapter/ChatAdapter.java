package ru.delightfire.delight.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ru.delightfire.delight.R;
import ru.delightfire.delight.entity.support.ChatMessage;

/**
 * Created by sergei on 02.11.2015.
 */
public class ChatAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private Context context;
    private List<ChatMessage> listChatMessages;

    public ChatAdapter(Context context, List<ChatMessage> listChatMessages) {
        this.context = context;
        this.listChatMessages = listChatMessages;
    }

    @Override
    public int getCount() {
        return listChatMessages.size();
    }

    @Override
    public Object getItem(int position) {
        return listChatMessages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        layoutInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ChatMessage chatMessageItem = listChatMessages.get(position);

        if (convertView == null) {
            if (chatMessageItem.isYourMsg()) {
                convertView = layoutInflater.inflate(R.layout.element_right_chat_dialog,
                        null);
            } else {
                convertView = layoutInflater.inflate(R.layout.element_left_chat_dialog,
                        null);
            }
        }
        TextView textMsg = (TextView) convertView.findViewById(R.id.textMsg);
        TextView nameMsg = (TextView) convertView.findViewById(R.id.nameMsg);
        textMsg.setText(chatMessageItem.getMessage());
        nameMsg.setText(chatMessageItem.getName());
        return convertView;
    }

}
