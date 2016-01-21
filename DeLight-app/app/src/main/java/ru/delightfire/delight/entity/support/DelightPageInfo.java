package ru.delightfire.delight.entity.support;

import android.widget.BaseAdapter;

/**
 * Created by scaredChatsky on 21.01.2016.
 */
public class DelightPageInfo {

    public DelightPageInfo(String title, BaseAdapter adapter) {
        this.title = title;
        this.adapter = adapter;
    }

    private BaseAdapter adapter;

    private String title;

    public BaseAdapter getAdapter(){
        return this.adapter;
    }

    public String getTitle() {
        return title;
    }
}
