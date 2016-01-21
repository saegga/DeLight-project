package ru.delightfire.delight.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.delightfire.delight.entity.DelightPageInfo;

/**
 * Created by scaredChatsky on 21.01.2016.
 */
public class DelightPagerAdapter extends PagerAdapter {

    private List<DelightPageInfo> pages = new ArrayList<>();

    public DelightPagerAdapter(List<DelightPageInfo> pages){
        this.pages = pages;
    }

    @Override
    public int getCount() {
        return pages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = pages.get(position).getView();

        container.addView(itemView);
        return itemView;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pages.get(position).getTitle();
    }
}
