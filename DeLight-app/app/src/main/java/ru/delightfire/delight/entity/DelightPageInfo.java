package ru.delightfire.delight.entity;

import android.view.View;

/**
 * Created by scaredChatsky on 21.01.2016.
 */
public class DelightPageInfo {

    public DelightPageInfo(String title, View view) {
        this.title = title;
        this.view = view;
    }

    private View view;

    private String title;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
