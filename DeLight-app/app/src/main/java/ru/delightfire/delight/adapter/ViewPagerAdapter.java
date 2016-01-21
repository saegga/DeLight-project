package ru.delightfire.delight.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ru.delightfire.delight.R;
import ru.delightfire.delight.entity.DelightPageInfo;

/**
 * Created by scaredChatsky on 21.01.2016.
 */
public class ViewPagerAdapter extends PagerAdapter {

    private List<DelightPageInfo> pages = new ArrayList<>();

    private Context context;

    public ViewPagerAdapter(List<DelightPageInfo> pages, Context context) {
        this.pages = pages;
        this.context = context;
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
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.element_schedule_page, container, false);
        ListView listView = (ListView) itemView.findViewById(R.id.lv_element_schedule_page);

        listView.setAdapter(pages.get(position).getAdapter());

        container.addView(itemView);
        return itemView;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pages.get(position).getTitle();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
